package pl.edu.agh.iisg.to.javafx.cw2.presenter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import pl.edu.agh.iisg.to.javafx.cw2.model.Category;
import pl.edu.agh.iisg.to.javafx.cw2.model.Transaction;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionEditDialogPresenter {

	private Transaction transaction;

	@FXML
	private TextField dateTextField;

	@FXML
	private TextField payeeTextField;

	@FXML
	private TextField categoryTextField;

	@FXML
	private TextField inflowTextField;
	
	private Stage dialogStage;
	
	private boolean approved;
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setData(Transaction transaction) {
		this.transaction = transaction;
		updateControls();
	}
	
	public boolean isApproved() {
		return approved;
	}

	@FXML
	private void handleOkAction(ActionEvent event) {
		if(isInputValid()){
			approved = true;
			updateModel();
			dialogStage.close();
		} else {

		}
	}
	
	@FXML
	private void handleCancelAction(ActionEvent event) {
		// TODO: implement
	}
	
	private boolean isInputValid() {
		// TODO: implement
		return true;
	}

	private void updateModel() {
		transaction.setDate(stringToDate(dateTextField.getText()));
		transaction.setPayee(payeeTextField.getText());
		transaction.setCategory(new Category(categoryTextField.getText()));
		BigDecimal inflow = new BigDecimal(0);
		try {
			inflow = stringToBigDecimal(inflowTextField.getText());
		} catch (ParseException e){
			e.printStackTrace();
		}
		transaction.setInflow(inflow);
	}

	private void updateControls() {
		dateTextField.setText(dateToString(transaction.getDate()));
		payeeTextField.setText(transaction.getPayee());
		categoryTextField.setText(transaction.getCategory().getName());
		inflowTextField.setText(transaction.getInflow().toString());
	}

	private String dateToString(LocalDate date){
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDateStringConverter converter = new LocalDateStringConverter(formatter, formatter);
		return converter.toString(date);
	}

	private LocalDate stringToDate(String date){
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDateStringConverter converter = new LocalDateStringConverter(formatter, formatter);
		return converter.fromString(date);
	}
	private BigDecimal stringToBigDecimal(String number) throws ParseException {
		DecimalFormat decimalFormatter = new DecimalFormat();
		decimalFormatter.setParseBigDecimal(true);
		return (BigDecimal) decimalFormatter.parse(number);
	}
}
