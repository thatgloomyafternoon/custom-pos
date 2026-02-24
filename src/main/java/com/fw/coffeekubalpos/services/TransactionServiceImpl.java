package com.fw.coffeekubalpos.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fw.coffeekubalpos.constants.PropertyConstants;
import com.fw.coffeekubalpos.entities.Transaction;
import com.fw.coffeekubalpos.repositories.TransactionRepository;
import com.fw.coffeekubalpos.services.dto.ReceiptContentJsonDTO;
import com.fw.coffeekubalpos.services.dto.ReceiptMenuJsonDTO;
import com.fw.coffeekubalpos.services.dto.TransactionListingDTO;
import com.fw.coffeekubalpos.utils.CurrencyStringFormatUtil;
import com.fw.coffeekubalpos.utils.MapperUtil;
import com.fw.coffeekubalpos.utils.PriceUtil;
import com.fw.coffeekubalpos.web.responses.OrderDetailsResponse;
import com.fw.coffeekubalpos.web.responses.SubmitPaymentResponse;
import com.fw.coffeekubalpos.web.responses.TransactionListingResponse;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

  private final ObjectMapper objMapper;
  private final MapperUtil mapperUtil;
  private final CurrencyStringFormatUtil currencyStringFormatUtil;
  private final TransactionRepository transactionRepository;
  private final OrderService orderService;
  private final OrderMenuService orderMenuService;

  public TransactionServiceImpl(MapperUtil mapperUtil,
                                CurrencyStringFormatUtil currencyStringFormatUtil,
                                TransactionRepository transactionRepository,
                                OrderService orderService,
                                OrderMenuService orderMenuService) {
    this.objMapper = new ObjectMapper();
    this.mapperUtil = mapperUtil;
    this.currencyStringFormatUtil = currencyStringFormatUtil;
    this.transactionRepository = transactionRepository;
    this.orderService = orderService;
    this.orderMenuService = orderMenuService;
  }

  @Override
  public SubmitPaymentResponse submitPayment(UUID orderId) throws JsonProcessingException {
    /** early-get transaction date */
    ZonedDateTime transactionDate = ZonedDateTime.now().plusHours(7);
    String transactionDateString = transactionDate
      .format(DateTimeFormatter.ofPattern(PropertyConstants.DATE_TIME_PATTERN));
    /** */
    OrderDetailsResponse odr = orderService.findOrderDetailsByOrderId(orderId);
    int totalPrice = 0;
    /** checking non-null and non-empty order-menu under order */
    if(odr.getMenuOrdered() == null || odr.getMenuOrdered().size() <= 0) {
      throw new IllegalArgumentException("ERROR");
    }
    /** create receipt content */
    ReceiptContentJsonDTO receiptContentJsonDTO = new ReceiptContentJsonDTO();
    receiptContentJsonDTO.setCashierName(((UserDetails)SecurityContextHolder.getContext()
      .getAuthentication()
      .getPrincipal())
      .getUsername()
    );
    receiptContentJsonDTO.setCustomerName(odr.getInfo().getCustomerName());
    receiptContentJsonDTO.setOrderedDate(odr.getInfo().getCreatedDate());
    receiptContentJsonDTO.setTransactionDate(transactionDateString);
    receiptContentJsonDTO.setMenuOrdered(odr.getMenuOrdered()
      .stream()
      .map(mapperUtil::mapOrderDetailsMenuOrderedResponseToReceiptMenuJsonDTO)
      .sorted(Comparator.comparing(ReceiptMenuJsonDTO::getMenuName))
      .collect(Collectors.toList())
    );
    totalPrice = PriceUtil.calculateTotalPrice(odr.getMenuOrdered());
    receiptContentJsonDTO.setTotalPriceString(currencyStringFormatUtil.format(totalPrice));
    /** create transaction entity */
    Transaction newTransaction = new Transaction();
    newTransaction.setCreatedDate(transactionDate);
    newTransaction.setCustomerName(odr.getInfo().getCustomerName());
    newTransaction.setTotalPrice(totalPrice);
    String receiptContentJson = null;
    receiptContentJson = objMapper.writeValueAsString(receiptContentJsonDTO);
    newTransaction.setReceiptContentJson(receiptContentJson);
    transactionRepository.save(newTransaction);
    /** */
    orderMenuService.deleteOrderMenusByOrderId(orderId);
    orderService.deleteOrderByOrderId(orderId);
    return null;
  }

  @Override
  public TransactionListingResponse getTransactionHistory() {
    List<Transaction> transactions = transactionRepository.findAll();
    List<TransactionListingDTO> transactionListings = transactions.stream()
      .map(mapperUtil::mapTransactionToTransactionListingDTO)
      .sorted(Comparator.comparing(TransactionListingDTO::getTransactionDate).reversed())
      .collect(Collectors.toList())
      .subList(0, transactions.size() > 20 ? 20 : transactions.size());
    transactionListings.forEach(tl -> tl.setTotalPriceString(currencyStringFormatUtil.format(tl.getTotalPrice())));
    /** convert time zone */
    transactionListings.forEach(tl -> tl.setDisplayedTransactionDate(tl.getTransactionDate().plusHours(7).format(DateTimeFormatter.ofPattern(PropertyConstants.DATE_TIME_PATTERN))));
    /** */
    TransactionListingResponse tlr = new TransactionListingResponse();
    tlr.setTransactions(transactionListings);
    tlr.setSize(transactionListings.size());
    tlr.setTotalRevenue(transactionListings.stream().mapToInt(TransactionListingDTO::getTotalPrice).sum());
    tlr.setDisplayedTotalRevenue("IDR " + currencyStringFormatUtil.format(tlr.getTotalRevenue()));
    return tlr;
  }

  @Override
  public TransactionListingResponse getTransactionHistory(String customerName,
                                                                String year,
                                                                String month,
                                                                String date) {
    int startMonth = month.equals("All months") ? 1 : Integer.parseInt(month);
    int endMonth = month.equals("All months") ? 12 : Integer.parseInt(month);
    int startDate = date.equals("All dates") ? 1 : Integer.parseInt(date);
    int endDate = date.equals("All dates") ? 31 : Integer.parseInt(date);
    List<TransactionListingDTO> transactionListings = transactionRepository.findAll()
      .stream()
      .filter(t -> Pattern.compile(Pattern.quote(customerName), Pattern.CASE_INSENSITIVE).matcher(t.getCustomerName()).find() &&
              t.getCreatedDate().withZoneSameInstant(ZoneOffset.ofHours(7)).getYear() == Integer.parseInt(year) &&
              t.getCreatedDate().withZoneSameInstant(ZoneOffset.ofHours(7)).getMonthValue() >= startMonth &&
              t.getCreatedDate().withZoneSameInstant(ZoneOffset.ofHours(7)).getMonthValue() <= endMonth &&
              t.getCreatedDate().withZoneSameInstant(ZoneOffset.ofHours(7)).getDayOfMonth() >= startDate &&
              t.getCreatedDate().withZoneSameInstant(ZoneOffset.ofHours(7)).getDayOfMonth() <= endDate)
      .map(mapperUtil::mapTransactionToTransactionListingDTO)
      .sorted(Comparator.comparing(TransactionListingDTO::getTransactionDate).reversed())
      .collect(Collectors.toList());
    transactionListings.forEach(tl -> tl.setTotalPriceString(currencyStringFormatUtil.format(tl.getTotalPrice())));
    /** convert time zone */
    transactionListings.forEach(tl -> tl.setDisplayedTransactionDate(tl.getTransactionDate().plusHours(7).format(DateTimeFormatter.ofPattern(PropertyConstants.DATE_TIME_PATTERN))));
    /** */
    TransactionListingResponse tlr = new TransactionListingResponse();
    tlr.setTransactions(transactionListings);
    tlr.setSize(transactionListings.size());
    tlr.setTotalRevenue(transactionListings.stream().mapToInt(TransactionListingDTO::getTotalPrice).sum());
    tlr.setDisplayedTotalRevenue("IDR " + currencyStringFormatUtil.format(tlr.getTotalRevenue()));
    return tlr;
  }

  @Override
  public ReceiptContentJsonDTO getReceiptContentByTransactionId(UUID transactionId) throws JsonMappingException, JsonProcessingException {
    Optional<Transaction> optTransaction = transactionRepository.findById(transactionId);
    if(!optTransaction.isPresent()) {
      throw new IllegalArgumentException("Transaction not found");
    }
    Transaction transaction = optTransaction.get();
    return objMapper.readValue(transaction.getReceiptContentJson(), ReceiptContentJsonDTO.class);
  }

}
