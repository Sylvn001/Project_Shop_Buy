PGDMP     +            
    	    x            Shop_Buy    10.14    12.4     �
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �
           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �
           1262    16423    Shop_Buy    DATABASE     �   CREATE DATABASE "Shop_Buy" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';
    DROP DATABASE "Shop_Buy";
                postgres    false            �            1259    16435    tb_prod    TABLE     �   CREATE TABLE public.tb_prod (
    pro_id integer NOT NULL,
    pro_nome character varying(35),
    pro_categ character varying(20),
    pro_desc text,
    pro_prec numeric(6,2)
);
    DROP TABLE public.tb_prod;
       public            postgres    false            �            1259    16433    tb_prod_pro_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tb_prod_pro_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.tb_prod_pro_id_seq;
       public          postgres    false    197            �
           0    0    tb_prod_pro_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.tb_prod_pro_id_seq OWNED BY public.tb_prod.pro_id;
          public          postgres    false    196            o
           2604    16438    tb_prod pro_id    DEFAULT     p   ALTER TABLE ONLY public.tb_prod ALTER COLUMN pro_id SET DEFAULT nextval('public.tb_prod_pro_id_seq'::regclass);
 =   ALTER TABLE public.tb_prod ALTER COLUMN pro_id DROP DEFAULT;
       public          postgres    false    196    197    197            �
          0    16435    tb_prod 
   TABLE DATA           R   COPY public.tb_prod (pro_id, pro_nome, pro_categ, pro_desc, pro_prec) FROM stdin;
    public          postgres    false    197          �
           0    0    tb_prod_pro_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.tb_prod_pro_id_seq', 9, true);
          public          postgres    false    196            q
           2606    16443    tb_prod tb_prod_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.tb_prod
    ADD CONSTRAINT tb_prod_pkey PRIMARY KEY (pro_id);
 >   ALTER TABLE ONLY public.tb_prod DROP CONSTRAINT tb_prod_pkey;
       public            postgres    false    197            �
   s   x�3����+.I-Rp�K-J��tJM�LI,��MTH�RA2�%�ɉ��z\F�^�e�
Ήi�W���HI��L��/��MNLK��KLI*44��,IZ��
���H*F��� k.�     