// @Author Battal Roni Özcan
package com.example.bilabonnement.models.damage;

public class DamageReport {
    private final int reportId;
    private final int rentalId;
    private final String description;

    public DamageReport(int id, int subscription_id, String description) {
        this.reportId = id;
        this.rentalId = subscription_id;
        this.description = description;
    }

    public int getReportId() {
        return reportId;
    }

    public int getRentalId() {
        return rentalId;
    }

    public String getDescription() {
        return description;
    }
}
