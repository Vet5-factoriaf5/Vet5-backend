package org.factoriaf5.happypaws.appointment;

public class AppointmentException extends RuntimeException {

    public AppointmentException(String message) {
        super(message);
    }

    public static AppointmentException MaxAppointmentsPerDay() {
        return new AppointmentException("Límite de 10 citas por día alcanzado");
    }

    public static AppointmentException AppointmentNotFound() {
        return new AppointmentException("Cita no encontrada");
    }

    public static AppointmentException InvalidAppointmentDate() {
        return new AppointmentException("Fecha y hora de la cita inválida");
    }
}
