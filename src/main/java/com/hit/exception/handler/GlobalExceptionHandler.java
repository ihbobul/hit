package com.hit.exception.handler;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.server.exceptions.response.ErrorContext;
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Produces
@Singleton
@Requires(classes = {ExceptionHandler.class})
public class GlobalExceptionHandler implements ExceptionHandler<RuntimeException, HttpResponse<?>> {

  private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  private final ErrorResponseProcessor<?> errorResponseProcessor;

  public GlobalExceptionHandler(ErrorResponseProcessor<?> errorResponseProcessor) {
    this.errorResponseProcessor = errorResponseProcessor;
  }

  @Override
  public HttpResponse<?> handle(HttpRequest request, RuntimeException exception) {
    String errorMessage = exception.getMessage() != null ? exception.getMessage()
        : "An unexpected error occurred.";

    LOG.error("Exception encountered: {} - {}", exception.getClass().getSimpleName(),
        errorMessage);

    HttpStatus httpStatus = getHttpStatus(exception);

    return errorResponseProcessor.processResponse(ErrorContext.builder(request)
        .cause(exception)
        .errorMessage(errorMessage)
        .build(), HttpResponse.status(httpStatus));
  }

  private HttpStatus getHttpStatus(RuntimeException exception) {
    return switch (exception.getClass().getSimpleName()) {
      case "EmailAlreadyExistsException", "UsernameAlreadyExistsException" -> HttpStatus.CONFLICT;
      default -> HttpStatus.INTERNAL_SERVER_ERROR;
    };
  }
}
