package com.smarcity.BusinessLayer;

public class AnalysisResult {
    private boolean collisionDetected;
    private String details;

    public AnalysisResult(boolean c, String d) {
        this.collisionDetected = c;
        this.details = d;
    }

    public boolean isCollisionDetected() {
        return collisionDetected;
    }

    public void setCollisionDetected(boolean collisionDetected) {
        this.collisionDetected = collisionDetected;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
