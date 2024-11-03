CREATE TABLE supplements
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL    UNIQUE,
    created_at TIMESTAMP                NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP                NOT NULL DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE supplements IS 'サプリメントテーブル';
COMMENT ON COLUMN supplements.id IS 'サプリメントID';
COMMENT ON COLUMN supplements.name IS 'サプリメント名';

CREATE TABLE items
(
    id                     UUID PRIMARY KEY,
    name                   VARCHAR(255) NOT NULL,
    quantity               INT          NOT NULL,
    dosage_per_use         INT          NOT NULL,
    daily_intake_frequency INT          NOT NULL,
    supply_days            INT          NOT NULL,
    expired_at             DATE         NOT NULL,
    start_at               DATE         NOT NULL,
    end_at                 DATE         NOT NULL,
    supplement_id          INT REFERENCES supplements (id),
    created_at             TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at             TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE items IS '商品テーブル';
COMMENT ON COLUMN items.id IS '商品ID';
COMMENT ON COLUMN items.name IS 'サプリメント名';
COMMENT ON COLUMN items.quantity IS '総量';
COMMENT ON COLUMN items.dosage_per_use IS '1回の摂取量';
COMMENT ON COLUMN items.daily_intake_frequency IS '1日の摂取回数';
COMMENT ON COLUMN items.supply_days IS '供給日数';
COMMENT ON COLUMN items.expired_at IS '賞味期限';
COMMENT ON COLUMN items.start_at IS '摂取開始日';
COMMENT ON COLUMN items.end_at IS '摂取終了日';
COMMENT ON COLUMN items.supplement_id IS 'サプリメントID';
