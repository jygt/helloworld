package com.example.helloWorld.mail;

import com.example.helloWorld.TUrl;

import java.util.List;

public interface SendJunkMailService {
    boolean sendJunkMail(List<TUrl> urlList);
}
