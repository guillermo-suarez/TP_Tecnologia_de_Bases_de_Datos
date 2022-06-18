CREATE OR REPLACE PROCEDURE PRC_CERRARCERT (pIdObra obra.idobra%type, pError OUT number)
IS
 vNroCerti certipago.nrocertificado%type;
 vIdFoja foja.idfoja%type;
 vHayCerti number;
BEGIN
    vNroCerti := fun_getultimonrocertipago(pIdObra); --Recuperamos el ultimo certificado
    SELECT c.idfoja into vIdFoja FROM CERTIOBRA c WHERE c.nrocertificado = vNroCerti AND c.idobra = pIdObra; --Recuperamos la foja
    vHayCerti := fun_estacertificada(vIdFoja); --Vemos si la foja tiene asociada una foja cerrada o no
    IF (vHayCerti = 1) THEN
      UPDATE CERTIPAGO cp
      SET cp.abierto = 0;
      pError:=0; --No hay error, se cerró el certificado
    ELSE IF (vHayCerti=0) THEN
      pError:=1; --No hay un certificado que cerrar
    ELSE
      pError:=2; --El ultimo certificado ya esta cerrado
    END IF;
    END IF;
END;
/
