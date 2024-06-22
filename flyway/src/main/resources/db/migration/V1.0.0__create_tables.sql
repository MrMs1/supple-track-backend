CREATE TABLE supplement_group
(
    name       VARCHAR(255) PRIMARY KEY NOT NULL,
    created_at TIMESTAMP                NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP                NOT NULL DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE supplement_group IS 'サプリメントグループテーブル';
COMMENT ON COLUMN supplement_group.name IS 'サプリメントグループ名';

CREATE TABLE supplements
(
    id                     UUID PRIMARY KEY,
    name                   VARCHAR(255) NOT NULL,
    quantity               INT          NOT NULL,
    dosage_per_use         INT          NOT NULL,
    daily_intake_frequency INT          NOT NULL,
    expired_at             DATE         NOT NULL,
    start_at               DATE         NOT NULL,
    end_at                 DATE         NOT NULL,
    group_name             VARCHAR(255) REFERENCES supplement_group (name),
    created_at             TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at             TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE supplements IS 'サプリメントテーブル';
COMMENT ON COLUMN supplements.id IS 'サプリメントID';
COMMENT ON COLUMN supplements.name IS 'サプリメント名';
COMMENT ON COLUMN supplements.quantity IS '総量';
COMMENT ON COLUMN supplements.dosage_per_use IS '1回の摂取量';
COMMENT ON COLUMN supplements.daily_intake_frequency IS '1日の摂取回数';
COMMENT ON COLUMN supplements.expired_at IS '賞味期限';
COMMENT ON COLUMN supplements.start_at IS '摂取開始日';
COMMENT ON COLUMN supplements.end_at IS '摂取終了日';
COMMENT ON COLUMN supplements.group_name IS 'サプリメントグループ名';
