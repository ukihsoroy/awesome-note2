package org.ko.oauth.service;


import org.ko.oauth.domain.OauthClientDetailsEntity;

public interface IClientDetailsService {

    void save(OauthClientDetailsEntity client);
}
