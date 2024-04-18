package org.ko.oauth.repository;

import org.ko.oauth.domain.OauthClientDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthClientDetailsRepository extends JpaRepository<OauthClientDetailsEntity, String> {


}
