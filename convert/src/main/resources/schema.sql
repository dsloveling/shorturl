CREATE TABLE `t_shorturl` (
  `id` bigint(11) NOT NULL,
  `c_key` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `c_source` varchar(1024) NOT NULL,
  `dt_updatetime` timestamp(6) NULL DEFAULT NULL,
  `c_short` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
