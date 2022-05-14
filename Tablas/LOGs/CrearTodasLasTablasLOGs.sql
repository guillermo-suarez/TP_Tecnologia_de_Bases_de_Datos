create table LOG_CERTIOBRA
(
  idobra         NUMBER(5) not null,
  nrocertificado NUMBER(3) not null,
  nrocerobra     NUMBER(3) not null,
  idfoja         NUMBER(5),
  operacion      VARCHAR2(10) not null,
  usuario        VARCHAR2(20) not null,
  fecha          DATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;

create table LOG_CERTIPAGO
(
  idobra         NUMBER(5) not null,
  nrocertificado NUMBER(3) not null,
  fechacert      DATE,
  abierto        NUMBER(1),
  idempresa      NUMBER(4) not null,
  operacion      VARCHAR2(10) not null,
  usuario        VARCHAR2(20) not null,
  fecha          DATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;

create table LOG_CONCEPTO
(
  idconcepto  NUMBER(3) not null,
  denconcepto VARCHAR2(70) not null,
  operacion   VARCHAR2(10) not null,
  usuario     VARCHAR2(20) not null,
  fecha       DATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;

create table LOG_CONCEPTOSXCERTIF
(
  idobra         NUMBER(5) not null,
  nrocertificado NUMBER(3) not null,
  idconcepto     NUMBER(3) not null,
  importeacuant  NUMBER(15,2),
  importe        NUMBER(15,2),
  operacion      VARCHAR2(10) not null,
  usuario        VARCHAR2(20) not null,
  fecha          DATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;

create table LOG_CONCEPTOSXOBRA
(
  idobra     NUMBER(5) not null,
  idconcepto NUMBER(3) not null,
  ordenimp   NUMBER(2) not null,
  porcentaje NUMBER(5,2) not null,
  operacion  VARCHAR2(10) not null,
  usuario    VARCHAR2(20) not null,
  fecha      DATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;

create table LOG_EMPRESA
(
  idempresa   NUMBER(4) not null,
  razonsocial VARCHAR2(70),
  cuit        NUMBER(12),
  operacion   VARCHAR2(10) not null,
  usuario     VARCHAR2(20) not null,
  fecha       DATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

create table LOG_FOJA
(
  idfoja    NUMBER(5) not null,
  idobra    NUMBER(5),
  fecha     DATE,
  operacion VARCHAR2(10) not null,
  usuario   VARCHAR2(20) not null,
  fechaop   DATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;

create table LOG_FOJADET
(
  idfoja         NUMBER(5) not null,
  iditem         NUMBER(8) not null,
  idobra         NUMBER(5) not null,
  avaacuanterior NUMBER(18,2),
  avaactual      NUMBER(18,2),
  monto          NUMBER(18,2),
  operacion      VARCHAR2(10) not null,
  usuario        VARCHAR2(20) not null,
  fecha          DATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;

create table LOG_ITEM
(
  iditem     NUMBER(8) not null,
  idobra     NUMBER(5) not null,
  idtipoitem NUMBER(2),
  denitem    VARCHAR2(120),
  orden      NUMBER(2),
  operacion  VARCHAR2(10) not null,
  usuario    VARCHAR2(20) not null,
  fecha      DATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

create table LOG_ITEMCOSTO
(
  iditem            NUMBER(8) not null,
  idobra            NUMBER(5) not null,
  idredeterminacion NUMBER(2) not null,
  costo             NUMBER(18,2),
  operacion         VARCHAR2(10) not null,
  usuario           VARCHAR2(20) not null,
  fecha             DATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

create table LOG_LOCALIDAD
(
  idlocalidad NUMBER(3) not null,
  nomloc      VARCHAR2(40),
  cp          VARCHAR2(10),
  operacion   VARCHAR2(10) not null,
  usuario     VARCHAR2(20) not null,
  fecha       DATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

create table LOG_OBRA
(
  idobra       NUMBER(5) not null,
  idlocalidad  NUMBER(3),
  idtipcontrat NUMBER(2),
  idempresa    NUMBER(4),
  nomobra      VARCHAR2(250),
  fecinicio    DATE,
  plazo_mes    NUMBER(3),
  numobra      NUMBER(5),
  porflete     NUMBER(5,2),
  porgastos    NUMBER(5,2),
  poruti       NUMBER(5,2),
  ivaviv       NUMBER(5,2),
  ivainfra     NUMBER(5,2),
  operacion    VARCHAR2(10) not null,
  usuario      VARCHAR2(20) not null,
  fecha        DATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

create table LOG_REDETERMINACION
(
  idredeterminacion NUMBER(2) not null,
  fecharedet        DATE,
  fechadesde        DATE,
  fechahasta        DATE,
  nroresolucion     VARCHAR2(210),
  operacion         VARCHAR2(10) not null,
  usuario           VARCHAR2(20) not null,
  fecha             DATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

create table LOG_TIPOCONTRATACION
(
  idtipcontrat  NUMBER(2) not null,
  dentipcontrat VARCHAR2(30),
  operacion     VARCHAR2(10) not null,
  usuario       VARCHAR2(20) not null,
  fecha         DATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

create table LOG_TIPOITEM
(
  idtipoitem NUMBER(2) not null,
  dentipo    VARCHAR2(60),
  operacion  VARCHAR2(10) not null,
  usuario    VARCHAR2(20) not null,
  fecha      DATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
