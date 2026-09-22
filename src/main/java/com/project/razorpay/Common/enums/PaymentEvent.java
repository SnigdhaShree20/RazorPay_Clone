package com.project.razorpay.Common.enums;

public enum PaymentEvent {
    AUTHORIZED_ATTEMPT,
    AUTHORIZE_SUCCESS,
    AUTHORIZE_FAIL,
    CAPTURE_SUCCESS,
    CAPTURE_FAIL,
    CAPTURE_REQUEST,
    REFUND_INIT,
    REFUND_COMPLETE,
    SETTLE,
    CANCEL,
    CAPTURE_TIMEOUT
}
