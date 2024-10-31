package ru.vadim.naujavaprjct.exception;

public class ReportException extends CustomException{

    public ReportException() {
        super(ExceptionType.REPORT_ERROR, "При создании отчета произошла ошибка");
    }
}
