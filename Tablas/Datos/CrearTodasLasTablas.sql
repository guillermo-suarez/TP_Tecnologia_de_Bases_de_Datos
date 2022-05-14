/*==============================================================*/
/* Table: CERTIOBRA                                             */
/*==============================================================*/
create table CERTIOBRA 
(
   IDOBRA               NUMBER(5)            not null,
   NROCERTIFICADO       NUMBER(3)            not null,
   NROCEROBRA           NUMBER(3)            not null,
   IDFOJA               NUMBER(5),
   constraint PK_CERTIOBRA primary key (IDOBRA, NROCERTIFICADO, NROCEROBRA)
);

/*==============================================================*/
/* Table: CERTIPAGO                                             */
/*==============================================================*/
create table CERTIPAGO 
(
   IDOBRA               NUMBER(5)            not null,
   NROCERTIFICADO       NUMBER(3)            not null,
   FECHACERT            DATE,
   ABIERTO              NUMBER(1),
   constraint PK_CERTIPAGO primary key (IDOBRA, NROCERTIFICADO)
);

/*==============================================================*/
/* Table: CONCEPTO                                              */
/*==============================================================*/
create table CONCEPTO 
(
   IDCONCEPTO           NUMBER(3)            not null,
   DENCONCEPTO          VARCHAR2(70),
   constraint PK_CONCEPTO primary key (IDCONCEPTO)
);

/*==============================================================*/
/* Table: CONCEPTOSXCERTIF                                      */
/*==============================================================*/
create table CONCEPTOSXCERTIF 
(
   IDOBRA               NUMBER(5)            not null,
   NROCERTIFICADO       NUMBER(3)            not null,
   IDCONCEPTO           NUMBER(3)            not null,
   IMPORTE              NUMBER(15,2),
   constraint PK_CONCEPTOSXCERTIF primary key (IDOBRA, NROCERTIFICADO, IDCONCEPTO)
);

/*==============================================================*/
/* Table: CONCEPTOSXOBRA                                        */
/*==============================================================*/
create table CONCEPTOSXOBRA 
(
   IDOBRA               NUMBER(5)            not null,
   IDCONCEPTO           NUMBER(3)            not null,
   ORDENIMP             NUMBER(2),
   CALCFONDOREPARO      NUMBER(1),
   constraint PK_CONCEPTOSXOBRA primary key (IDOBRA, IDCONCEPTO)
);

/*==============================================================*/
/* Table: EMPRESA                                               */
/*==============================================================*/
create table EMPRESA 
(
   IDEMPRESA            NUMBER(4)            not null,
   RAZONSOCIAL          VARCHAR(70),
   CUIT                 NUMBER(12),
   constraint PK_EMPRESA primary key (IDEMPRESA)
);

/*==============================================================*/
/* Table: FOJA                                                  */
/*==============================================================*/
create table FOJA 
(
   IDFOJA               NUMBER(5)            not null,
   IDOBRA               NUMBER(5),
   FECHA                DATE,
   constraint PK_FOJA primary key (IDFOJA)
);

/*==============================================================*/
/* Table: FOJADET                                               */
/*==============================================================*/
create table FOJADET 
(
   IDFOJA               NUMBER(5)            not null,
   IDITEM               NUMBER(8)            not null,
   IDOBRA               NUMBER(5)            not null,
   AVAACUANTERIOR       NUMBER(18,2),
   AVAACTUAL            NUMBER(18,2),
   constraint PK_FOJADET primary key (IDFOJA, IDITEM, IDOBRA)
);

/*==============================================================*/
/* Table: ITEM                                                  */
/*==============================================================*/
create table ITEM 
(
   IDITEM               NUMBER(8)            not null,
   IDOBRA               NUMBER(5)            not null,
   IDTIPOITEM           NUMBER(2),
   DENITEM              VARCHAR(120),
   ORDEN                NUMBER(1),
   constraint PK_ITEM primary key (IDITEM, IDOBRA)
);

/*==============================================================*/
/* Table: ITEMCOSTO                                             */
/*==============================================================*/
create table ITEMCOSTO 
(
   IDITEM               NUMBER(8)            not null,
   IDOBRA               NUMBER(5)            not null,
   IDREDETERMINACION    NUMBER(2)            not null,
   COSTO                NUMBER(18,2),
   constraint PK_ITEMCOSTO primary key (IDITEM, IDOBRA, IDREDETERMINACION)
);

/*==============================================================*/
/* Table: LOCALIDAD                                             */
/*==============================================================*/
create table LOCALIDAD 
(
   IDLOCALIDAD          NUMBER(3)            not null,
   NOMLOC               VARCHAR(40),
   CP                   VARCHAR(10),
   constraint PK_LOCALIDAD primary key (IDLOCALIDAD)
);

/*==============================================================*/
/* Table: OBRA                                                  */
/*==============================================================*/
create table OBRA 
(
   IDOBRA               NUMBER(5)            not null,
   IDLOCALIDAD          NUMBER(3),
   IDTIPCONTRAT         NUMBER(2),
   IDEMPRESA            NUMBER(4),
   NOMOBRA              VARCHAR(250),
   FECINICIO            DATE,
   PLAZO_MES            NUMBER(3),
   NUMOBRA              NUMBER(5),
   PORFLETE             NUMBER(5,2),
   PORGASTOS            NUMBER(5,2),
   PORUTI               NUMBER(5,2),
   IVAVIV               NUMBER(5,2),
   IVAINFRA             NUMBER(5,2),
   constraint PK_OBRA primary key (IDOBRA)
);

/*==============================================================*/
/* Table: REDETERMINACION                                       */
/*==============================================================*/
create table REDETERMINACION 
(
   IDREDETERMINACION    NUMBER(2)            not null,
   FECHAREDET           DATE,
   FECHADESDE           DATE,
   FECHAHASTA           DATE,
   NRORESOLUCION        VARCHAR(10),
   constraint PK_REDETERMINACION primary key (IDREDETERMINACION)
);

/*==============================================================*/
/* Table: TIPOCONTRATACION                                      */
/*==============================================================*/
create table TIPOCONTRATACION 
(
   IDTIPCONTRAT         NUMBER(2)            not null,
   DENTIPCONTRAT        VARCHAR(30),
   constraint PK_TIPOCONTRATACION primary key (IDTIPCONTRAT)
);

/*==============================================================*/
/* Table: TIPOITEM                                              */
/*==============================================================*/
create table TIPOITEM 
(
   IDTIPOITEM           NUMBER(2)            not null,
   DENTIPO              VARCHAR(60),
   constraint PK_TIPOITEM primary key (IDTIPOITEM)
);

alter table CERTIOBRA
   add constraint FK_CERTIOBR_REFERENCE_CERTIPAG foreign key (IDOBRA, NROCERTIFICADO)
      references CERTIPAGO (IDOBRA, NROCERTIFICADO);

alter table CERTIOBRA
   add constraint FK_CERTIOBR_REFERENCE_FOJA foreign key (IDFOJA)
      references FOJA (IDFOJA);

alter table CERTIPAGO
   add constraint FK_CERTIPAG_REFERENCE_OBRA foreign key (IDOBRA)
      references OBRA (IDOBRA);

alter table CONCEPTOSXCERTIF
   add constraint FK_CONCEPTO_REFERENCE_CERTIPAG foreign key (IDOBRA, NROCERTIFICADO)
      references CERTIPAGO (IDOBRA, NROCERTIFICADO);

alter table CONCEPTOSXCERTIF
   add constraint FK_CONCEPTO_REF_CONXCERTIF foreign key (IDCONCEPTO)
      references CONCEPTO (IDCONCEPTO);

alter table CONCEPTOSXOBRA
   add constraint FK_CONCEPTO_REF_CONCEPTOXOBRA foreign key (IDCONCEPTO)
      references CONCEPTO (IDCONCEPTO);

alter table CONCEPTOSXOBRA
   add constraint FK_CONCEPTO_REFERENCE_OBRA foreign key (IDOBRA)
      references OBRA (IDOBRA);

alter table FOJA
   add constraint FK_FOJA_REFERENCE_OBRA foreign key (IDOBRA)
      references OBRA (IDOBRA);

alter table FOJADET
   add constraint FK_FOJADET_REFERENCE_ITEM foreign key (IDITEM, IDOBRA)
      references ITEM (IDITEM, IDOBRA);

alter table FOJADET
   add constraint FK_FOJADET_REFERENCE_FOJA foreign key (IDFOJA)
      references FOJA (IDFOJA);

alter table ITEM
   add constraint FK_ITEM_REFERENCE_OBRA foreign key (IDOBRA)
      references OBRA (IDOBRA);

alter table ITEM
   add constraint FK_ITEM_REFERENCE_TIPOITEM foreign key (IDTIPOITEM)
      references TIPOITEM (IDTIPOITEM);

alter table ITEMCOSTO
   add constraint FK_ITEMCOST_REFERENCE_REDETERM foreign key (IDREDETERMINACION)
      references REDETERMINACION (IDREDETERMINACION);

alter table ITEMCOSTO
   add constraint FK_ITEMCOST_REFERENCE_ITEM foreign key (IDITEM, IDOBRA)
      references ITEM (IDITEM, IDOBRA);

alter table OBRA
   add constraint FK_OBRA_REFERENCE_LOCALIDA foreign key (IDLOCALIDAD)
      references LOCALIDAD (IDLOCALIDAD);

alter table OBRA
   add constraint FK_OBRA_REFERENCE_TIPOCONT foreign key (IDTIPCONTRAT)
      references TIPOCONTRATACION (IDTIPCONTRAT);

alter table OBRA
   add constraint FK_OBRA_REFERENCI_EMPRESA foreign key (IDEMPRESA)
      references EMPRESA (IDEMPRESA);
