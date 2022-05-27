CREATE OR REPLACE FUNCTION FUN_GETULTIMONROCERTIOBRA(pIdObra obra.idobra%type, pIdCertiPago certipago.nrocertificado%type)
RETURN certiobra.nrocerobra%type
IS
  vNroCerti certiobra.nrocerobra%type;
  vCount number;
BEGIN
  SELECT COUNT(*) INTO vCount FROM CERTIOBRA CO
  WHERE CO.IDOBRA = pIdObra;
  IF vCount>0 THEN
    SELECT MAX(CO.NROCEROBRA) INTO vNroCerti FROM CERTIOBRA CO
    WHERE CO.IDOBRA=pIdObra AND CO.NROCERTIFICADO = pIdCertiPago;
  ELSE
    vNroCerti := 0;
  END IF;
  RETURN vNroCerti;
END;
/
