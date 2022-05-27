CREATE OR REPLACE PROCEDURE PRC_CREARFOJA(pNumObra obra.numobra%type)
is
vIdObra obra.idobra%type;
vIdUltimaFoja foja.idfoja%type;
vIdUltimaFojaCerti foja.idfoja%type;
vConteo number;
BEGIN
  DBMS_OUTPUT.put_line('Comienzo');
  --RECUPERAR IDOBRA
  SELECT FUN_RecuperarIdObra(pNumObra) into vIdObra from DUAL;
  DBMS_OUTPUT.put_line(vIdObra);
  SELECT COUNT(*) INTO vConteo FROM FOJA WHERE idObra=vIdObra;
  IF vConteo != 0 THEN --hay fojas
    --RECUPERAR ULTIMA FOJA
    SELECT MAX(idFoja) INTO vIdUltimaFoja FROM FOJA WHERE idObra=vIdObra;
    --RECUPERAR ULTIMA FOJA CERTIFICADA
    SELECT COUNT(*) INTO vConteo FROM CERTIOBRA WHERE idObra=vidObra;
    IF vConteo != 0 THEN --Hay certificados
       SELECT MAX(idFoja) INTO vIdUltimaFojaCerti FROM CERTIOBRA WHERE idObra=vidObra;
       IF vIdUltimaFojaCerti = vIdUltimaFoja THEN --La ultima foja está certificada
          INSERT INTO FOJA
          VALUES(-1,vIdObra,CURRENT_DATE);
          PRC_CREARFOJADETS(vIdObra, vIdUltimaFoja, SEQ_ID_FOJA.CURRVAL);
       ELSE --La ultima foja no está certificada
          DBMS_OUTPUT.put_line('No se puede crear foja');
       END IF;
    ELSE --No hay certificados
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
