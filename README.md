# csvreader

An RFC 4180 CSV parser that handles quotes, escaped quotes and embedded newlines.

## Usage

```java
List<List<String>> rows = CsvReader.parse("name,note\nAda,\"likes, commas\"\n");
```

## Test

```bash
mvn test
```

## License

MIT
