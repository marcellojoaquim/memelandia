package com.marcello.auth_service.dto;

import java.util.List;

public record UserAuthDto(String username, String password, List<String> roles) {
}
