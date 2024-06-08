CREATE TABLE supplement_group
(
    name VARCHAR(255) PRIMARY KEY NOT NULL
);

CREATE TABLE supplements
(
    id                     UUID PRIMARY KEY,
    name                   VARCHAR(255) NOT NULL,
    quantity               INT          NOT NULL,
    dosage_per_use         INT          NOT NULL,
    daily_intake_frequency INT          NOT NULL,
    expired_at             DATE         NOT NULL,
    start_at               DATE         NOT NULL,
    end_dat                DATE         NOT NULL,
    group_name VARCHAR(255) REFERENCES supplement_group (name)
);
