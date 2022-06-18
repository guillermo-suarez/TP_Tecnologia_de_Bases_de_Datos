CREATE OR REPLACE PROCEDURE PRC_CREARFOJA(pNumObra obra.numobra%type,
                                                         pError OUT number)
is
vIdObra obra.idobra%type;
vIdUltimaFoja foja.idfoja%type;
vHayCerti number;
BEGIN
  -- RECUPERAR IDOBRA
  vIdObra := FUN_GETIdObra(pNumObra);
  if vIdObra != (-1) then 
      vIdUltimaFoja := FUN_GETULTIMOIDFOJA(vIdObra);
    IF vIdUltimaFoja != (-1) THEN --hay fojas    
      --VER SI LA ULTIMA FOJA ESTA CERTIFICADA
      vHayCerti := FUN_ESTACERTIFICADA(vIdUltimaFoja);
      IF vHayCerti = 2 THEN --La última FOJA esta certificada y cerrada  
        dbms_output.put_line('FOJA creada correctamente');
        INSERT INTO FOJA
        VALUES(-1,vIdObra,CURRENT_DATE);
        PRC_CREARFOJADETS(vIdObra, vIdUltimaFoja, SEQ_ID_FOJA.CURRVAL);
        commit;
        pError := 0; -- Todo bien
      ELSIF vHayCerti = 1 then --La ultima FOJA esta certificada, pero el CERTIPAGO no esta cerrado
        pError := 2; -- La última FOJA de esa OBRA esta certificada, pero el CERTIPAGO sigue abierto
      ELSE --La última FOJA no esta certificada
        pError := 3; -- La última FOJA de esa OBRA no esta certificada
      END IF;
    ELSE -- La obra no tiene fojas, se asume que se puede crear la foja
      INSERT INTO FOJA
      VALUES(-1, vIdObra, CURRENT_DATE);
      PRC_CREARFOJADETS(vIdObra, -1, SEQ_ID_FOJA.CURRVAL);
      commit;
      pError := 0; -- Todo bien
    END IF;
  else
    pError := 1; -- No existe OBRA con ese NUMOBRA
  end if;
END;

/* Valores de pError:
0 ---> todo bien, se creo la FOJA correctamente 
1 ---> no existe OBRA con el NUMOBRA a utilizar
2 ---> existe la OBRA, pero su última FOJA está certificada, pero ese CERTIPAGO sigue abierto
3 ---> existe la OBRA, pero su última FOJA ni siquiera esta certificada */
/
