Interface (Device): Grundläggande kontrakt som alla smarta enheter i huset måste uppfylla (turnOn(), turnOff(), getStatus()).

Abstrakt klass (AbstractDevice): Hanterar gemensam logik som enhetsnamn, IP-adress, strömstatus och loggning.

Konkreta klasser med Arv (Light, Thermometer): Utökar grundfunktionen med egna specifika egenskaper (ljusstyrka/färg vs temperatur).

Konkret klass direkt mot Interface (Speaker): En tredjepartshögtalare som inte passar i den vanliga hårdvarustrukturen men ändå kan stängas av/sättas på via appen.
