package com.example.espino.scaneat.interfaces;

public interface ILogin {

    interface View{
        void setMessage(String message, int idView);
    }

    interface LoginPresenter {
        void validateCredentials(String user, String password);
    }

    interface SignUpPresenter{
        void validateCredentials(String user, String email, String passwd, String confirm);
    }


}
