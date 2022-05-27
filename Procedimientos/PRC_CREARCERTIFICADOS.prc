CREATE OR REPLACE PROCEDURE PRC_CREARCERTIFICADOS (pNumObra obra.numobra%type)
IS
  vIdObra obra.idobra%type;
  vIdFoja foja.idfoja%type;
  vHayCerti number;
BEGIN
  --Recuperamos el ID de la obra
  SELECT fun_getidobra(pNumObra) INTO vIdObra FROM DUAL;
  --Recuperamos la ultima foja
  SELECT fun_getultimoidfoja(vIdObra) INTO vIdFoja FROM DUAL;
  --Vemos si la ultima foja esta certificada
  vHayCerti := fun_estacertificada(vIdFoja);
  IF vHayCerti = 0 THEN --Si no está certificada
    --Funcion recuperar ultimo id certipago
    --Insertar certipago, abierto = 1
    --Insertar certiobra
    DBMS_OUTPUT.put_line('algo');
  ELSE
    DBMS_OUTPUT.put_line('No se puede certificar la ultima foja');
  END IF;
END;
/
