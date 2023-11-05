"# sistema-experto-forward-chaining-java" 
# Sistema Experto Analisis Medico



 Se ocupara FWC (forward-chaining)
 
- **Preview**
  
  Principal

  ![preview img](/preview/se_fwc.jpg)
  
  Script 
  ```	
NUMERICO 
TEMP 

ESCALAR 
TOS= [ SI,NO ] ,
CANSANCIO= [ SI,NO ] ,
DOLORCUERPO = [ SI,NO ] ,
FIEBRE= [ SI,NO ] ,
EMPFERMEDAD= [ GRIPE],
ATENCION= [OTORRINO,MEDICOGENERAL]
REGLA 
IF TEMP >37
        THEN 
         FIEBRE=SI
IF TOS = SI && CANSANCIO = SI && DOLORCUERPO = SI && FIEBRE = SI
        THEN 
         ENFERMEDAD = GRIPE
IF ENFERMEDAD = GRIPE
	THEN 
        ATENCION = MEDICOGENERAL

IF ENFERMEDAD = GRIPE
	THEN 
        ATENCION = OTORRINO

IF ATENCION = OTORRINO
	THEN 
        TRATAMIENTO = JARABE

IF ATENCION = MEDICOGENERAL
	THEN 
        TRATAMIENTO = VACUNA

BASEHECHO
 [Temp = 38 , TOS = SI, CANSANCIO = SI,DOLORCUERPO = SI]
  
  ```
  
  Ejecutado

  ![preview img](/preview/se_fwc_meta.jpg)
  
  