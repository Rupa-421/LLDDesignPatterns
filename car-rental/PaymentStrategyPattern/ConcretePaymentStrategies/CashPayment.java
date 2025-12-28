package paymentStrategyPattern.ConcretePaymentStrategies;

import paymentStrategyPattern.PaymentStrategy;

public class CashPayment implements PaymentStrategy{
    @Override
    public void processPayment(double amount){
        System.out.pritnln("Processing cash payment of $ "+ amount);

    }
}