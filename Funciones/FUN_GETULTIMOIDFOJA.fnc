CREATE OR REPLACE FUNCTION FUN_GETULTIMOIDFOJA(pIdObra obra.idobra%type)
RETURN foja.idfoja%type
IS
vUltimoIdFoja foja.idfoja%type;
vCount number;
BEGIN
  SELECT COUNT(f.idfoja) INTO vCount FROM FOJA f WHERE f.idobra = pIdObra;
  IF (vCount > 0) THEN --Si hay fojas devuelve el ultimo numero  de id
    SELECT MAX(f.idfoja) INTO vUltimoIdFoja
    FROM FOJA f
    WHERE f.idobra = pIdObra;
  ELSE --Si no hay fojas devuelve -1
    vUltimoIdFoja := -1;
  END IF;
  RETURN vUltimoIdFoja;
END;
/
