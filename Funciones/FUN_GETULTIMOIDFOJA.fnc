CREATE OR REPLACE FUNCTION FUN_GETULTIMOIDFOJA(pIdObra obra.idobra%type)
RETURN foja.idfoja%type
IS
vUltimoIdFoja foja.idfoja%type;
BEGIN
  SELECT MAX(f.idfoja) INTO vUltimoIdFoja
  FROM FOJA f
  WHERE f.idobra = pIdObra;
  RETURN vUltimoIdFoja;
END;
/
