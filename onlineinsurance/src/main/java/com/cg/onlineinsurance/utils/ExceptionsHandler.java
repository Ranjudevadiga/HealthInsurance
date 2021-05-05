package com.cg.onlineinsurance.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.onlineinsurance.exception.CustomerDetailsEmptyException;
import com.cg.onlineinsurance.exception.CustomerListEmptyException;
import com.cg.onlineinsurance.exception.CustomerNotFoundException;
import com.cg.onlineinsurance.exception.CustomerPolicyListEmptyException;
import com.cg.onlineinsurance.exception.CustomerPolicyNotFoundException;
import com.cg.onlineinsurance.exception.DuplicateCustomerException;
import com.cg.onlineinsurance.exception.DuplicateCustomerPolicyException;
import com.cg.onlineinsurance.exception.DuplicatePolicyException;
import com.cg.onlineinsurance.exception.EmptyPolicyException;
import com.cg.onlineinsurance.exception.InvalidUserException;
import com.cg.onlineinsurance.exception.PolicyListEmptyException;
import com.cg.onlineinsurance.exception.PolicyNotFoundException;

import com.cg.onlineinsurance.exception.DuplicateCustomerDetailException;
import com.cg.onlineinsurance.exception.PolicyActiveException;



@ControllerAdvice
public class ExceptionsHandler  {
	
	
	@ExceptionHandler(value=DuplicatePolicyException.class)
	public ResponseEntity<Object> exception( DuplicatePolicyException exception){
		return new ResponseEntity<Object>("Policy already exists",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=EmptyPolicyException.class)
	public ResponseEntity<Object> exception(EmptyPolicyException exception){
		return new ResponseEntity<Object>("cannnot add an empty policy",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=PolicyNotFoundException.class)
	public ResponseEntity<Object> exception(PolicyNotFoundException exception){
		return new ResponseEntity<Object>("Policy not found",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=PolicyListEmptyException.class)
	public ResponseEntity<Object> exception(PolicyListEmptyException exception){
		return new ResponseEntity<Object>("Policy List is Empty",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=CustomerNotFoundException.class)
	public ResponseEntity<Object> exception(CustomerNotFoundException exception){
		return new ResponseEntity<Object>("Could not find any registered customer by this credential",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=DuplicateCustomerException.class)
	public ResponseEntity<Object> exception(DuplicateCustomerException exception){
		return new ResponseEntity<Object>("Customer already exists",HttpStatus.NOT_FOUND);
		}
	@ExceptionHandler(value=DuplicateCustomerPolicyException.class)
	public ResponseEntity<Object> exception(DuplicateCustomerPolicyException exception){
		return new ResponseEntity<Object>("This policy has already been bought ",HttpStatus.NOT_FOUND);
		}
	@ExceptionHandler(value=CustomerPolicyNotFoundException.class)
	public ResponseEntity<Object> exception(CustomerPolicyNotFoundException exception){
		return new ResponseEntity<Object>("Invalid Customer's policy Details",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=PolicyActiveException.class)
	public ResponseEntity<Object> exception(PolicyActiveException exception){
		return new ResponseEntity<Object>("Cannot renew an active policy",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=DuplicateCustomerDetailException.class)
	public ResponseEntity<Object> exception(DuplicateCustomerDetailException exception){
		return new ResponseEntity<Object>("Details for this customer already exists",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=CustomerListEmptyException.class)
	public ResponseEntity<Object> exception(CustomerListEmptyException exception){
		return new ResponseEntity<Object>("There are no customers",HttpStatus.NOT_FOUND);
		}
	
	
	@ExceptionHandler(value=CustomerPolicyListEmptyException.class)
	public ResponseEntity<Object> exception(CustomerPolicyListEmptyException exception){
		return new ResponseEntity<Object>("Customer has not bought any policies",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=InvalidUserException.class)
	public ResponseEntity<Object> exception(InvalidUserException exception){
		return new ResponseEntity<Object>("Invalid user credentials",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=CustomerDetailsEmptyException.class)
	public ResponseEntity<Object> exception(CustomerDetailsEmptyException exception){
		return new ResponseEntity<Object>("This customer has not entered his/her details.Kindly Enter the CustomerDetails before any policy can be purchased",HttpStatus.NOT_FOUND);
		}
	
	
}



