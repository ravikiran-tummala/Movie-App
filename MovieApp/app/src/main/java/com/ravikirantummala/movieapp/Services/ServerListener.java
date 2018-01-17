package com.ravikirantummala.movieapp.Services;

/**
 * Created by ravikirantummala on 17/01/18.
 */

public interface ServerListener{
    void onSuccessResponse(String response);
    void onErrorResponse(Exception error);
}