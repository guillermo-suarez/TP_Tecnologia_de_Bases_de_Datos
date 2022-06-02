CREATE OR REPLACE NONEDITIONABLE PROCEDURE PRC_CREARFOJA(pNumObra obra.numobra%type)
is
vIdObra obra.idobra%type;
vIdUltimaFoja foja.idfoja%type;
vHayCerti number;
BEGIN
  --RECUPERAR IDOBRA
  SELECT FUN_GETIdObra(pNumObra) into vIdObra from DUAL;
  SELECT FUN_GETULTIMOIDFOJA(vIdObra) INTO vIdUltimaFoja FROM DUAL;
  IF vIdUltimaFoja != (-1) THEN --hay fojas    
    --VER SI LA ULTIMA FOJA ESTA CERTIFICADA
    vHayCerti := FUN_ESTACERTIFICADA(vIdUltimaFoja);
    IF vHayCerti = 2 THEN --La última FOJA esta certificada y cerrada       
          dbms_output.put_line('FOJA creada correctamente');
          INSERT INTO FOJA
          VALUES(-1,vIdObra,CURRENT_DATE);
          PRC_CREARFOJADETS(vIdObra, vIdUltimaFoja, SEQ_ID_FOJA.CURRVAL);
    ELSIF vHayCerti = 1 then --La ultima FOJA esta certificada, pero el CERTIPAGO no esta cerrado
       DBMS_OUTPUT.put_line('No se puede crear foja, ya que el CERTIPAGO de la última FOJA sigue abierto');
    ELSE --La última FOJA no esta certificada
      dbms_output.put_line('No se puede crear foja, ya que la última FOJA no está certificada');
    END IF;
  ELSE --no hay fojas
    --se asume que se puede crear la foja
    INSERT INTO FOJA
    VALUES(-1, vIdObra, CURRENT_DATE);
    PRC_CREARFOJADETS(vIdObra, -1, SEQ_ID_FOJA.CURRVAL);
  END IF;
END;
/
