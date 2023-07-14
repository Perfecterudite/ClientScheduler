package Company.Model;

public class Report {

    public String appointmentMonth;
    public int appointmentTotal;
    public String appointmentType;

    /**
     * @param appointmentMonth
     * * @param appointmentType
     * @param appointmentTotal
     */
    public Report(String appointmentMonth, int appointmentTotal) {
        this.appointmentMonth = appointmentMonth;
        this.appointmentTotal = appointmentTotal;
        this.appointmentType = appointmentType;
    }

    public Report(String appointmentType) {
        this.appointmentType = appointmentType;
        this.appointmentTotal = appointmentTotal;
    }

    /**
     * @return appointmentMonth
     */
    public String getAppointmentMonth() {

        return appointmentMonth;
    }

    /**
     * @return appointmentType
     */

    public String getAppointmentType() {

        return appointmentType;
    }

    /**
     * @return appointmentTotal
     */
    public int getAppointmentTotal() {

        return appointmentTotal;
    }
}
