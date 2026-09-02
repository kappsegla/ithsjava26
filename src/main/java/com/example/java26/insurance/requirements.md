## Kravspecifikation: Bilförsäkrings‑prismotor

🧭 1. Syfte
Systemet ska beräkna ett rekommenderat försäkringspris för en bil baserat på riskfaktorer kopplade till bilens egenskaper och förarens historik. Systemet ska ge ett konsekvent, reproducerbart pris utan behov av loopar eller externa datakällor.

🧩 2. Indata
Programmet ska ta emot följande värden:

Bilmodell (String)

Årlig körsträcka i mil (int)

Antal olyckor senaste åren (int)

Antal år med körkort (int)

Indata ska valideras så att:

Körsträcka ≥ 0

Olyckor ≥ 0

Körkortstid ≥ 0

🧮 3. Beräkningslogik

3.1 Grundpris

Systemet ska utgå från ett grundpris baserat på bilmodell.
Exempel (fiktiva värden):

Standardbil: 3000 kr
Sportbil: 5000 kr
SUV: 4000 kr
Elbil: 3500 kr

3.2 Justeringar baserat på körsträcka

< 1000 mil/år: −5 %
1000–2000 mil/år: ingen ändring
2000 mil/år: +10 %

3.3 Justeringar baserat på olyckor

0 olyckor: −10 %
1 olycka: +10 %
≥ 2 olyckor: +25 %

3.4 Justeringar baserat på körkortstid

< 3 år: +20 %
3–10 år: ingen ändring
10 år: −10 %

3.5 Slutpris

Slutpriset ska beräknas genom att applicera alla relevanta procentuella justeringar på grundpriset.

📦 4. Utdata

Programmet ska presentera följande information:

Slutgiltigt pris
Riskkategori som bestäms enligt:
Slutpris < 3000 kr → “Låg risk”
3000–5000 kr → “Medel risk”
5000 kr → “Hög risk”
