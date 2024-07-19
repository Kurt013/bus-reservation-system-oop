package com.shivajivarma.brs.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import com.shivajivarma.brs.model.bean.ReservationBean;
import com.shivajivarma.brs.model.dao.ReservationDAO;
import com.shivajivarma.brs.model.dao.ReserveDAO;
import com.shivajivarma.brs.model.entity.Reserve;
import com.shivajivarma.brs.utility.IOHelpers;


public class ReserveService implements Service{
	
	/**
	 * Session variable which holds account details of the customer until he logout.
	 */
	private Reserve reserve;
	public static ClassPathXmlApplicationContext dbApplicationContext;
	
	public ReserveService(){
		dbApplicationContext = new ClassPathXmlApplicationContext("spring-jdbc.xml");
	}
	
	public void setModel(Reserve reserve){
		this.reserve = reserve;
	}
		
	public boolean cancelTicket() throws EmptyResultDataAccessException{
		ReserveDAO reserveDAO = dbApplicationContext.getBean("reserveDAO", ReserveDAO.class);
		
		Reserve dbRecord = reserveDAO.findById(reserve.getId());
		if(dbRecord.getPassengerID() == reserve.getPassengerID()){
			reserveDAO.deleteById(reserve.getId());
			return true;
		}else{
			return false;
		}
	}
	
	public List<ReservationBean> getReservationHistory(int pid) throws EmptyResultDataAccessException{
		
		ReservationDAO reservationDAO = dbApplicationContext.getBean("reservationDAO", ReservationDAO.class);
		
		List<ReservationBean> reservationList = reservationDAO.findByPid(pid);
		return reservationList;
	}
	
	public List<Integer> getOccupiedSeatNumbers(int bid, String date) throws EmptyResultDataAccessException{
		
		ReserveDAO reserveDAO = dbApplicationContext.getBean("reserveDAO", ReserveDAO.class);
		
		List<Integer> occupiedSeatNumbers = reserveDAO.getSeatNumbersByBusAndDate(bid, date);
		return occupiedSeatNumbers;
	}
	
	  public void printTickets(List<ReservationBean> tickets) {
        // Load ticket-head.html from classpath
        String html = loadResourceAsString("html/ticket-head.html");

        // Append ticket cards for each reservation
        for (ReservationBean reservationBean : tickets) {
            html += createTicketCard(reservationBean);
        }

        // Load ticket-end.html from classpath
        html += loadResourceAsString("html/ticket-end.html");

        // Print the generated HTML
        IOHelpers.printHTML(html, "tickets");
    }

    private String loadResourceAsString(String resourcePath) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream != null) {
                try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name()).useDelimiter("\\A")) {
									return scanner.hasNext() ? scanner.next() : "";
								}
            } else {
                throw new IOException("Resource not found: " + resourcePath);
            }
        } catch (IOException e) {
            System.err.println("Error loading resource: " + e.getMessage());
            return ""; // Handle error gracefully
        }
    }
	
	private static String createTicketCard(ReservationBean reservationBean){
		int fare;
		boolean getDiscounted = reservationBean.getDiscounted();
		if (getDiscounted) {
			fare = (int) (Math.ceil(reservationBean.getFare() * 0.8));
		}
		else {
			fare = reservationBean.getFare();
		}
		
		String card = "<!DOCTYPE html>\n" +
		"<html lang=\"en\">\n" +
		"<head>\n" +
		"    <meta charset=\"UTF-8\">\n" +
		"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
		"    <title>Ticket System</title>\n" +
		"<style>" +
		"* {" +
		"box-sizing: border-box;" +
		"}" +
		"html, body {" +
		"height: 100%;" +
		"margin: 0;" +
		"}" +
		"body {" +
		"@import url('https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700');" +
		"font-family: 'Ubuntu', sans-serif;" +
		"background-color: #DC020B;" +
		"height: 100%;" +
		"-webkit-font-smoothing: antialiased;" +
		"-moz-osx-font-smoothing: grayscale;" +
		"text-align: center;" +
		"color: #1c1c1c;" +
		"display: flex;" +
		"justify-content: center;" +
		"}" +
		".ticket-system {" +
		"max-width: 385px;" +
		"}" +
		".ticket-system .top {" +
		"display: flex;" +
		"align-items: center;" +
		"flex-direction: column;" +
		"}" +
		".ticket-system .top .title {" +
		"font-weight: normal;" +
		"font-size: 1.4em;" +
		"text-align: left;" +
		"margin-left: 20px;" +
		"margin-bottom: 20px;" +
		"color: #fff;" +
		"}" +
		".ticket-system .top .printer {" +
		"width: 90%;" +
		"height: 20px;" +
		"border: 5px solid #fff;" +
		"border-radius: 10px;" +
		"box-shadow: 1px 3px 3px 0px rgba(0, 0, 0, 0.2);" +
		"}" +
		".ticket-system .receipts-wrapper {" +
		"overflow: hidden;" +
		"margin-top: -10px;" +
		"padding-bottom: 10px;" +
		"}" +
		".ticket-system .receipts {" +
		"width: 100%;" +
		"display: flex;" +
		"align-items: center;" +
		"flex-direction: column;" +
		"transform: translateY(-510px);" +
		"animation-duration: 2.5s;" +
		"animation-delay: 500ms;" +
		"animation-name: print;" +
		"animation-fill-mode: forwards;" +
		"}" +
		".ticket-system .receipts .receipt {" +
		"padding: 25px 30px;" +
		"text-align: left;" +
		"width: 88%;" +
		"background-color: #fff;" +
		"border-radius: 10px 10px 20px 20px;" +
		"box-shadow: 1px 3px 8px 3px rgba(0, 0, 0, 0.2);" +
		"}" +
		".ticket-system .receipts .receipt .airliner-logo {" +
		"max-width: 100px;" +
		"margin-bottom: 20px;" +
		"}" +
		".ticket-system .receipts .receipt .details {" +
		"display: flex;" +
		"justify-content: space-between;" +
		"flex-wrap: wrap;" +
		"}" +
		".ticket-system .receipts .receipt .details .item {" +
		"display: flex;" +
		"flex-direction: column;" +
		"min-width: 100px;" +
		"}" +
		".one { flex-basis: 100%; }" +
		".ticket-system .receipts .receipt .details .item span {" +
		"font-size: 0.8em;" +
		"color: rgba(28, 28, 28, 0.7);" +
		"font-weight: 500;" +
		"}" +
		".ticket-system .receipts .receipt .details .item h3 {" +
		"margin-top: 10px;" +
		"margin-bottom: 25px;" +
		"}" +
		".ticket-system .receipts .receipt.qr-code {" +
		"height: 110px;" +
		"min-height: unset;" +
		"position: relative;" +
		"border-radius: 20px 20px 10px 10px;" +
		"display: flex;" +
		"align-items: center;" +
		"}" +
		".ticket-system .receipts .receipt.qr-code::before {" +
		"content: \"\";" +
		"background: linear-gradient(to right, #fff 50%, #DC020B 50%);" +
		"background-size: 22px 4px, 100% 4px;" +
		"height: 4px;" +
		"width: 90%;" +
		"display: block;" +
		"left: 0;" +
		"right: 0;" +
		"top: -1px;" +
		"position: absolute;" +
		"margin: auto;" +
		"}" +
	
		".ticket-system .receipts .receipt.qr-code .description {" +
		"margin-left: 20px;" +
		"}" +
		".ticket-system .receipts .receipt.qr-code .description h2 {" +
		"margin: 0 0 5px 0;" +
		"font-weight: 500;" +
		"font-size: 18px;" +
		"color: #DC020B;"+
		"}" +
		".ticket-system .receipts .receipt.qr-code .description p {" +
		"margin: 0;" +
		"font-weight: 400;" +
		"font-size: 15px;" +
		"}" +
		"@keyframes print {" +
		"0% {" +
		"transform: translateY(-510px);" +
		"}" +
		"35% {" +
		"transform: translateY(-395px);" +
		"}" +
		"70% {" +
		"transform: translateY(-140px);" +
		"}" +
		"100% {" +
		"transform: translateY(0);" +
		"}" +
		"}" +
		"</style>" +
		"</head>\n" +
		"<body>\n" +
		"<main class=\"ticket-system\">\n" +
		"   <div class=\"top\">\n" +
		"   <h1 class=\"title\"><i>Wait a second, your ticket is being printed...</i></h1>\n" +
		"   <div class=\"printer\">\n" +
		"   </div>\n" +
		"   <div class=\"receipts-wrapper\">\n" +
		"      <div class=\"receipts\">\n" +
		"         <div class=\"receipt\">\n" +
		"            <img class=\"airliner-logo\" src=\"https://jacliner.com/images/logo-jacliner.jpg\" alt=\"Airliner Logo\">\n" +
		"            <div class=\"details\">\n" +
		"               <div class=\"item one\">\n" +
		"                  <span>Date</span>\n" +
		"                  <h3>"+reservationBean.getDt()+"</h3>\n" +
		"               </div>\n" +
		"               <div class=\"item\">\n" +
		"                  <span>Ticket No.</span>\n" +
		"                  <h3>" +reservationBean.getId()+ "</h3>\n" +
		"               </div>\n" +
		"               <div class=\"item\">\n" +
		"                  <span>Passenger ID</span>\n" +
		"                  <h3>"+reservationBean.getPassengerID()+"</h3>\n" +
		"               </div>\n" +
		"               <div class=\"item\">\n" +
		"                  <span>Bus No.</span>\n" +
		"                  <h3>"+reservationBean.getBusID()+"</h3>\n" +
		"               </div>\n" +
		"               <div class=\"item\">\n" +
		"                  <span>Seat No.</span>\n" +
		"                  <h3>"+reservationBean.getSeat()+"</h3>\n" +
		"               </div>\n" +
		"               <div class=\"item\">\n" +
		"                  <span>Dept. Time</span>\n" +
		"                  <h3>"+reservationBean.getDeparturetime()+"</h3>\n" +
		"               </div>\n" +
		"               <div class=\"item\">\n" +
		"                  <span>Arr. Time</span>\n" +
		"                  <h3>"+reservationBean.getArrivaltime()+"</h3>\n" +
		"               </div>\n" +
		"               <div class=\"item\">\n" +
		"                  <span>Terminal</span>\n" +
		"                  <h3>"+reservationBean.getOrigin()+"</h3>\n" +
		"               </div>\n" +
		"               <div class=\"item\">\n" +
		"                  <span>Destination</span>\n" +
		"                  <h3>"+reservationBean.getDestination()+"</h3>\n" +
		"               </div>\n" +
		"               <div class=\"item\">\n" +
		"                  <span>Cost</span>\n" +
		"                  <h3> Php "+ fare +".00 </h3>\n" +
		"               </div>\n" +
		"            </div>\n" +
		"         </div>\n" +
		"         <div class=\"receipt qr-code\">\n" +
		"            <div class=\"description\">\n" +
		"               <h2><b>Successfully Reserved!<b></h2>\n" +
		"            </div>\n" +
		"         </div>\n" +
		"      </div>\n" +
		"   </div>\n" +
		"</main>\n" +
		"</body>\n" +
		"</html>";
		
		return card;
	}
	
	public int reserve(Reserve reserve){

		ReserveDAO reserveDAO = dbApplicationContext.getBean("reserveDAO", ReserveDAO.class);
		
		 return reserveDAO.save(reserve);
	}
	
	 protected void finalize(){
		dbApplicationContext.close();
		dbApplicationContext = null;
	 }
	
}
