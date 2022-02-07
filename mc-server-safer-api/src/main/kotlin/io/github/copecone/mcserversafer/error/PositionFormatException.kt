package io.github.copecone.mcserversafer.error

class PositionFormatException(x: String, y: String, z: String): Exception("([$x] [$y] [$z])는 잘못된 위치 표기입니다")