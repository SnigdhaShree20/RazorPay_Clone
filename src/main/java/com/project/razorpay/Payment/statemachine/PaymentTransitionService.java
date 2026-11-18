package com.project.razorpay.Payment.statemachine;

import com.project.razorpay.Common.enums.PaymentActor;
import com.project.razorpay.Common.enums.PaymentEvent;
import com.project.razorpay.Common.enums.PaymentStatus;
import com.project.razorpay.Payment.entity.Payment;
import com.project.razorpay.Payment.entity.PaymentTransitionLog;
import com.project.razorpay.Payment.repository.PaymentTransitionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentTransitionService {

    private final PaymentTransitionLogRepository paymentTransitionLogRepository;
    private final PaymentStateMachine paymentStateMachine;

    public PaymentStatus apply(Payment payment, PaymentEvent event) {
        PaymentStatus next=paymentStateMachine.transition(payment.getStatus(),event);
        payment.setStatus(next);

        PaymentTransitionLog log=PaymentTransitionLog.builder()
                .payment(payment)
                .fromStatus(payment.getStatus())
                .event(event)
                .toStatus(next)
                .actor(PaymentActor.SYSTEM)
                .occuredAt(LocalDateTime.now())
                .build();

        paymentTransitionLogRepository.save(log);

        return next;

    }
}
