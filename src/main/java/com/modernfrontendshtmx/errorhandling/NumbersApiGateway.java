package com.modernfrontendshtmx.errorhandling;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import org.springframework.stereotype.Component;

@Component
public class NumbersApiGateway {
  private static final RandomGenerator RANDOM_GENERATOR =
      RandomGeneratorFactory.getDefault().create();

  private final NumbersApi api;

  public NumbersApiGateway(NumbersApi api) { this.api = api; }

  public String getFact(int number) {
    int randomNumber = RANDOM_GENERATOR.nextInt(3);
    switch (randomNumber) {
            case 0 -> {
                return api.getFact(number);
            }
            case 1 -> {
                try {
                    Thread.sleep(5000);
                    return api.getFact(number);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
            default -> throw new RuntimeException("Unable to get a fact aboud the number " + number);
        }
    }
}
