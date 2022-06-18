CREATE OR REPLACE PROCEDURE PRC_CREARCERTIFICADOS (pNumObra obra.numobra%type, pError OUT number)
IS
  vIdObra obra.idobra%type;
  vIdFoja foja.idfoja%type;
  vHayCerti number;
  vNroCertiPago certipago.nrocertificado%type;
  vNroCertiObra certiobra.nrocerobra%type;
  vEmpresa empresa.idempresa%type;
  vVacio boolean;  
BEGIN
  --Recuperamos el ID de la obra
  SELECT fun_getidobra(pNumObra) INTO vIdObra FROM DUAL;
  --Recuperamos la ultima foja
  SELECT fun_getultimoidfoja(vIdObra) INTO vIdFoja FROM DUAL;
  --Vemos si la ultima foja esta certificada
  vHayCerti := fun_estacertificada(vIdFoja);
  IF vHayCerti = 0 THEN --Si no est? certificada
     --Vemos si hay campos vacios
     vVacio := FUN_FOJAESTAVACIA(vIdFoja);   
     if vVacio then
       DBMS_OUTPUT.put_line('No se puede certificar una obra con items nulos');
       pError:=2;
     else
        --Recuperar ultimo num certipago
        vNroCertiPago := FUN_GETULTIMONROCERTIPAGO(vIdObra) + 1;  
        --Recuperar la empresa  
        SELECT o.idempresa INTO vEmpresa 
        FROM OBRA o WHERE O.IDOBRA = vIdObra;
        --Insertar certipago, abierto = 1
        INSERT INTO CERTIPAGO 
        VALUES(vIdObra, vNroCertiPago, CURRENT_DATE, 1, vEmpresa); 
        --Recuperar el ultimo numcertiobra      
        vNroCertiObra := fun_getultimonrocertiobra(vIdObra, vNroCertiPago) + 1;             
        --Insertar certiobra
        INSERT INTO CERTIOBRA
        VALUES (vIdObra, vNroCertiPago,vNroCertiObra, vIdFoja);  
        PRC_CrearConceptosXCertif (vIdObra, vIdFoja, vNroCertiObra);
        commit;
        pError:=0; --No hay error
     end if;  
  ELSE
    pError:=1;
    DBMS_OUTPUT.put_line('No se puede certificar la ultima foja porque ya esta certificada');
  END IF;
END;
/
