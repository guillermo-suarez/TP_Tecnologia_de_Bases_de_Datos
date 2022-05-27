CREATE OR REPLACE FUNCTION FUN_GETULTIMONROCERTIPAGO(pIdObra obra.idobra%type)
RETURN certipago.nrocertificado%type
IS
  vNroCerti certipago.nrocertificado%type;
  vCount number;
BEGIN
  SELECT COUNT(*) INTO vCount FROM CERTIPAGO CP
  WHERE CP.IDOBRA = pIdObra;
  IF vCount>0 THEN
    SELECT MAX(CP.NROCERTIFICADO) INTO vNroCerti FROM CERTIPAGO CP
    WHERE CP.IDOBRA = pIdObra;
  ELSE
    vNroCerti := 0;
  END IF;
  RETURN vNroCerti;
END;
/
