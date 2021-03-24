/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.account;

import java.io.Serializable;

/**
 *
 * @author TRONG DAT
 */
public class AccountCreateError implements Serializable{
    private String usernameLengthError;
    private String usernameDuplicate;
    private String passwordLengthError;
    private String passwordFormatError;
    private String confirmNotMatched;
    private String fullnameLengthError;
    private String emailDuplicate;
    public AccountCreateError(){
        
    }
    /**
     * @return the usernameLengthError
     */
    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    /**
     * @param usernameLengthError the usernameLengthError to set
     */
    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    /**
     * @return the usernameDupplicate
     */
    public String getUsernameDuplicate() {
        return usernameDuplicate;
    }

    /**
     * @param usernameDupplicate the usernameDupplicate to set
     */
    public void setUsernameDuplicate(String usernameDupplicate) {
        this.usernameDuplicate = usernameDupplicate;
    }

    /**
     * @return the passwordLengthError
     */
    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    /**
     * @param passwordLengthError the passwordLengthError to set
     */
    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    /**
     * @return the fullnameLengthError
     */
    public String getFullnameLengthError() {
        return fullnameLengthError;
    }

    /**
     * @param fullnameLengthError the fullnameLengthError to set
     */
    public void setFullnameLengthError(String fullnameLengthError) {
        this.fullnameLengthError = fullnameLengthError;
    }

    /**
     * @return the emailDupplicate
     */
    public String getEmailDuplicate() {
        return emailDuplicate;
    }

    /**
     * @param emailDupplicate the emailDupplicate to set
     */
    public void setEmailDuplicate(String emailDupplicate) {
        this.emailDuplicate = emailDupplicate;
    }

    /**
     * @return the emailNotExisted
     */

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    public String getPasswordFormatError() {
        return passwordFormatError;
    }

    public void setPasswordFormatError(String passwordFormatError) {
        this.passwordFormatError = passwordFormatError;
    }
    
    
}
