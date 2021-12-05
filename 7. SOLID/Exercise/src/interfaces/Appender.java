package interfaces;

import com.softuni.SOLID.enums.ReportLevel;
import enums.ReportLevel;

public interface Appender {
    void append(String time, String message, ReportLevel reportLevel);
    void setReportLevel(ReportLevel reportLevel);

    void setReportLevel(ReportLevel valueOf);
}
