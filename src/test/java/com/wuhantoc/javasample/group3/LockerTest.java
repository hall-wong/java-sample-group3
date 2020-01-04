package com.wuhantoc.javasample.group3;

import org.junit.jupiter.api.Test;

import static com.wuhantoc.javasample.group3.Locker.FULL_LOCKER_MESSAGE;
import static com.wuhantoc.javasample.group3.Locker.WRONG_TICKET_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LockerTest {

    private Locker initAvailableLocker() {
        return new Locker();
    }

    //存包正确
    @Test
    void should_generate_a_ticket_when_save_package_given_available_locker() {
        // given
        Locker locker = initAvailableLocker();
        // when
        SavePackageResult result = locker.savePackage();
        // then
        assertNotNull(result);
        assertTrue(result.isSuccessFlag());
        assertNull(result.getErrorMessage());
        assertNotNull(result.getTicket());
    }

    private Locker initFullLocker() {
        Locker locker = new Locker();
        locker.fillToTheFull();
        return locker;
    }

    //存包错误
    @Test
    void should_get_error_message_when_save_package_given_full_locker() {
        // given
        Locker locker = initFullLocker();
        // when
        SavePackageResult result = locker.savePackage();
        // then
        assertNotNull(result);
        assertFalse(result.isSuccessFlag());
        assertNull(result.getTicket());
        assertEquals(FULL_LOCKER_MESSAGE, result.getErrorMessage());
    }

    private String initCorrectTicket(Locker anyLocker){
        return anyLocker.generateCorrectTicket();
    }

    //正确取包
    @Test
    void should_get_package_when_get_package_given_correct_ticket() {
        //given
        Locker locker = initAvailableLocker();
        String correctTicket = initCorrectTicket(locker);
        //when
        GetPackageResult result = locker.getPackage(correctTicket);
        //then
        assertNotNull(result);
        assertTrue(result.isSuccessFlag());
        assertNull(result.getErrorMessage());
    }

    private String initWrongTicket(Locker anyLocker){
        return anyLocker.generateWrongTicket();
    }

    //错误取包
    @Test
    void should_get_error_message_when_get_message_given_error_ticket() {
        //given
        Locker locker = initAvailableLocker();
        String wrongTicket = initWrongTicket(locker);
        //when
        GetPackageResult result = locker.getPackage(wrongTicket);
        //then
        assertNotNull(result);
        assertFalse(result.isSuccessFlag());
        assertEquals(WRONG_TICKET_MESSAGE, result.getErrorMessage());
    }


}
