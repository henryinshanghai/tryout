package com.henry.tryout.easy_coding.uint_test.write_unit_test_03.test_framework_01;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;

@Data
public class TicketSeller {
    private int inventory;
    private LocalTime closeTime;

    public void sell(int ticketAmount) {
        inventory -= ticketAmount;
    }

    public void refund(int ticketAmount) {
        inventory += ticketAmount;
    }

    public void setCloseTime(LocalTime localTime) {
        this.closeTime = localTime;
    }

    public boolean cloudSellAt(Serializable localTime) {
        return true;
    }
}
