CREATE TABLE consultations(
	id BIGINT NOT NULL auto_increment PRIMARY KEY,
	doctor_id BIGINT NOT NULL,
	patient_id BIGINT NOT NULL,
	date DATETIME NOT NULL,

	CONSTRAINT fk_consultations_doctor_id FOREIGN KEY(doctor_id) REFERENCES doctors(id),
	CONSTRAINT fk_consultation_patient_id FOREIGN KEY(patient_id) REFERENCES patients(id)
);