# ParcelGo Core Logistics & Tracking Platform

Лабораторна робота №3 з дисципліни **«Проєктування інформаційних систем»**  
Студентка: **Лящук Соломія**, група **ШІ-33**  
Викладач: **Яремченко О. Д.**  
Національний університет «Львівська політехніка», 2026

---

## 📌 Опис репозиторію

Цей репозиторій містить практичну емуляцію **Git Branching Strategy (GitHub Flow + Release Branches)** для доменного мікросервісу **`parcelgo-tracking`** інформаційної системи логістичної компанії **«ParcelGo»**.

### 🌟 Ключові концепції реалізації:
- **SDLC:** Essential SAFe (Scaled Agile Framework) — 5 крос-функціональних команд в Agile Release Train (ART), 4 Program Increments (PI) по 10 тижнів.
- **Міграція:** Strangler Fig Pattern + Debezium CDC для переходу зі старої системи без зупинки логістики (Zero Downtime, NFR-19, NFR-20).
- **Архітектура:** Event Sourcing (ASR-1), брокер Apache Kafka (ASR-2), ідемпотентність операцій (ASR-3, NFR-9).
- **Інфраструктура:** Private Cloud в українських ЦОД (De Novo / GigaCloud, Tier III, CON-3) із гео-розподіленою катастрофостійкістю між Києвом та Львовом (RPO=0, NFR-7).
- **CI/CD Quality Gates:** Unit pass rate 100%, Code coverage ≥80% lines / ≥70% branches, Mutation score ≥70%, SonarQube 0 Blocker/Critical, Trivy 0 High/Critical CVE, SLA відстеження <2 с при 5 000 req/s (NFR-1).
- **Деплоймент:** Canary Deployment через Argo Rollouts та Istio (5% → 25% → 50% → 100%) з автоматичним відкатом за <100 мс при помилках >0.1%.

---

## 🌿 Структура гілок та релізів у репозиторії

Репозиторій демонструє повний виробничий життєвий цикл релізу:

| Гілка / Тег | Призначення | Опис комітів |
|---|---|---|
| [`main`](https://github.com/SolomiaLi/parcelgo-tracking/tree/main) | Production-ready гілка | Захищена основна лінія коду, єдине джерело правди. |
| [`release/1.0.0`](https://github.com/SolomiaLi/parcelgo-tracking/tree/release/1.0.0) | Гілка стабілізації релізу | Підготовка реліз-кандидата `1.0.0-RC1` та hardening (додавання `IdempotencyGuard`). |
| [`feature/PG-103-kafka-producer-consumer`](https://github.com/SolomiaLi/parcelgo-tracking/tree/feature/PG-103-kafka-producer-consumer) | Гілка нової фічі | Інтеграція асинхронного Kafka-продюсера для пікових навантажень до 100 000 ТТН/хв (ASR-2, NFR-4). |
| [`hotfix/1.0.1`](https://github.com/SolomiaLi/parcelgo-tracking/tree/hotfix/1.0.1) | Екстрена латка безпеки | Терміновий security hotfix від тегу `v1.0.0` для захисту пошукового запиту ТТН від ін'єкцій (NFR-15). |
| [`tag: v1.0.0`](https://github.com/SolomiaLi/parcelgo-tracking/releases/tag/v1.0.0) | Виробничий реліз | Перший пілотний реліз ядра системи (Core Tracking, Event Sourcing, Idempotency Guard). |
| [`tag: v1.0.1`](https://github.com/SolomiaLi/parcelgo-tracking/releases/tag/v1.0.1) | Патч безпеки | Реліз екстреної латки безпеки. |

---

## 📜 Журнал історії Git (`git log --graph`)

```text
*   113c25e (HEAD -> main) merge: apply emergency hotfix/1.0.1 patch into main branch
|\  
| * 35035fd (tag: v1.0.1) fix(security): sanitize TTN query input against blind injection (NFR-15)
* |   8502307 merge: pull request #4 from feature/PG-103-kafka-producer-consumer
|\ \  
| |/  
|/|   
| * b8246b6 feat(PG-103): integrate Apache Kafka async producer for peak loads (ASR-2, NFR-4)
|/  
*   cf23429 (tag: v1.0.0) merge: release branch 1.0.0 into main for production deployment
|\  
| * a223c07 chore(release): finalize release 1.0.0 tag candidate
| * 5e73767 fix(release): add IdempotencyGuard to eliminate duplicate event processing (ASR-3, NFR-9)
| * 85a11f3 chore(release): bump version to 1.0.0-RC1 and prepare release candidate
|/  
*   bce8178 merge: pull request #3 from bugfix/PG-201-invalid-transition-guard
|\  
| * 2bb415e fix(PG-201): reject invalid transition attempts after final states (NFR-10)
|/  
*   f8f3ec3 merge: pull request #2 from feature/PG-102-event-sourcing (event sourcing engine)
|\  
| * bb8fa3c feat(PG-102): implement EventProjection to project event stream to current state
| * 4128a6c feat(PG-102): define EventStoreRepository interface for append-only storage
| * d767dcb feat(PG-102): add immutable DomainEvent record for audit log (NFR-14)
|/  
*   bef85a7 merge: pull request #1 from feature/PG-101-parcel-tracking (core tracking domain)
|\  
| * b512b89 feat(PG-101): add TrackingService query API with <2s SLA target (NFR-1)
| * 9bef24f feat(PG-101): implement ParcelState enum based on Lab2 state machine (ASR-1)
|/  
* 70f6ee5 chore: initial project repository structure
```
