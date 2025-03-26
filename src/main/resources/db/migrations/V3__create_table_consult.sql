CREATE TABLE consultation (
    id INT PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    scheduled_date_time DATETIME NOT NULL,
    reason TEXT,
    status VARCHAR(20) CHECK (status IN ('scheduled', 'completed', 'canceled')),
    FOREIGN KEY (patient_id) REFERENCES patient(id),
    FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);
