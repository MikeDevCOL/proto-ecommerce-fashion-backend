INSERT INTO
  colors (hex_code, name)
VALUES
  ('#111111', 'Black'),
  ('#F5F5F5', 'White'),
  ('#2F5D50', 'Green'),
  ('#1E3A5F', 'Navy'),
  ('#A64B3C', 'Rust');

INSERT INTO
  sizes (code, label, position)
VALUES
  ('S', 'Small', 1),
  ('M', 'Medium', 2),
  ('L', 'Large', 3);

INSERT INTO
  products (title, short_description, main_img_url)
VALUES
  (
    'Classic Cotton T-Shirt',
    'Soft everyday cotton T-shirt with a relaxed fit.',
    'https://placehold.co/600x600'
  ),
  (
    'Relaxed Linen Shirt',
    'Lightweight linen shirt for warm-weather layering.',
    'https://placehold.co/600x600'
  ),
  (
    'Everyday Denim Jacket',
    'A versatile denim jacket with a clean finish.',
    'https://placehold.co/600x600'
  ),
  (
    'Ribbed Knit Sweater',
    'A soft ribbed sweater for comfortable daily wear.',
    'https://placehold.co/600x600'
  ),
  (
    'Essential Hoodie',
    'A midweight hoodie with a roomy, comfortable fit.',
    'https://placehold.co/600x600'
  ),
  (
    'Straight Leg Jeans',
    'Classic straight-leg jeans made for everyday use.',
    'https://placehold.co/600x600'
  ),
  (
    'Utility Cargo Pants',
    'Practical cargo pants with a modern tapered shape.',
    'https://placehold.co/600x600'
  ),
  (
    'Pleated Midi Skirt',
    'A flowing pleated skirt with an easy midi length.',
    'https://placehold.co/600x600'
  ),
  (
    'Woven Summer Dress',
    'A breezy woven dress for sunny days.',
    'https://placehold.co/600x600'
  ),
  (
    'Tailored Blazer',
    'A polished blazer with a versatile tailored cut.',
    'https://placehold.co/600x600'
  ),
  (
    'Lightweight Windbreaker',
    'A packable layer for changing weather.',
    'https://placehold.co/600x600'
  ),
  (
    'Fleece Zip Jacket',
    'A warm fleece layer with a convenient front zip.',
    'https://placehold.co/600x600'
  ),
  (
    'Canvas Chore Coat',
    'A durable canvas coat with practical pockets.',
    'https://placehold.co/600x600'
  ),
  (
    'Soft Lounge Pants',
    'Comfortable lounge pants with an elastic waist.',
    'https://placehold.co/600x600'
  ),
  (
    'Long Sleeve Polo',
    'A classic long-sleeve polo in soft cotton.',
    'https://placehold.co/600x600'
  );

INSERT INTO
  product_variants (
    sku,
    product_id,
    color_id,
    size_id,
    original_price,
    sale_price,
    is_active
  )
SELECT
  CONCAT ('P', p.id, '-', c.id, '-', s.id),
  p.id,
  c.id,
  s.id,
  CAST(20 + p.id * 2 + s.position AS DECIMAL(12, 2)),
  CAST(15 + p.id * 2 + s.position AS DECIMAL(12, 2)),
  TRUE
FROM
  products p
  CROSS JOIN sizes s
  JOIN colors c ON c.id = MOD(p.id + s.position - 2, 5) + 1;

INSERT INTO
  product_images (img_url, product_variant_id)
SELECT
  CONCAT (
    'https://placehold.co/600x800?text=',
    REPLACE (p.title, ' ', '+'),
    '+',
    s.code
  ),
  v.id
FROM
  product_variants v
  JOIN products p ON p.id = v.product_id
  JOIN sizes s ON s.id = v.size_id;