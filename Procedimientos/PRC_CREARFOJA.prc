CREATE OR REPLACE PROCEDURE PRC_CREARFOJA(pNumObra obra.numobra%type)
is
vIdObra obra.idobra%type;
vIdUltimaFoja foja.idfoja%type;
vHayCerti number;
BEGIN
  DBMS_OUTPUT.put_line('Comienzo');
  --RECUPERAR IDOBRA
  SELECT FUN_GETIdObra(pNumObra) into vIdObra from DUAL;
  DBMS_OUTPUT.put_line(vIdObra);SELECT FUN_GETULTIMOIDFOJA(vIdObra) INTO vIdUltimaFoja FROM DUAL;
  IF vIdUltimaFoja != (-1) THEN --hay fojas    
    --VER SI LA ULTIMA FOJA ESTA CERTIFICADA
    vHayCerti := FUN_ESTACERTIFICADA(vIdUltimaFoja);
    IF vHayCerti = 2 THEN --Esta certificada y cerrada       
          INSERT INTO FOJA
          VALUES(-1,vIdObra,CURRENT_DATE);
          PRC_CREARFOJADETS(vIdObra, vIdUltimaFoja, SEQ_ID_FOJA.CURRVAL);
    ELSE --La ultima foja no esta certificada
       DBMS_OUTPUT.put_line('No se puede crear foja');
    END IF;
  ELSE --no hay fojas
    --se asume que se puede crear la foja
    INSERT INTO FOJA
    VALUES(-1, vIdObra, CURRENT_DATE);
    PRC_CREARFOJADETS(vIdObra, -1, SEQ_ID_FOJA.CURRVAL);
  END IF;
END;
/
