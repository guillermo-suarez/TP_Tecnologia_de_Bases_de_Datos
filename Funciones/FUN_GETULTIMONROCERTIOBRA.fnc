CREATE OR REPLACE NONEDITIONABLE FUNCTION FUN_GETULTIMONROCERTIOBRA(pIdObra obra.idobra%type)
RETURN certiobra.nrocerobra%type
IS
  vNroCerti certiobra.nrocerobra%type;
  vCount number;
BEGIN
  SELECT COUNT(*) INTO vCount FROM CERTIOBRA CO
  WHERE CO.IDOBRA = pIdObra;
  IF vCount > 0 THEN
    SELECT MAX(CO.NROCEROBRA) INTO vNroCerti FROM CERTIOBRA CO
    WHERE CO.IDOBRA = pIdObra;
  ELSE
    vNroCerti := 0;
  END IF;
  RETURN vNroCerti;
END;
/
