# JParserCombinator [![Build Status](https://travis-ci.org/ksgwr/JParserCombinator.svg?branch=master)](https://travis-ci.org/ksgwr/JParserCombinator)

## 概要

ParserCombinatorとは単純なParserを組み合わせて複雑なParserを構築する仕組みです。このライブラリではこのParserCombinatorを実装するためのParserInterfaceとParser失敗時の巻き戻しを容易にするMultiMarkableReaderを提供しています。
この実装例としてCSV,TSVParserと検索式をパースするためのQueryParserを実装しています。ParserInterfaceはStringとReaderクラスを自動的にMultiMarkableReaderに変換してくれるため利用者は入力サイズを意識する必要がありません。Parser実装側は逐次入力を処理することを考えて実装するだけです。

## インストール

[Version情報](https://github.com/ksgwr/JParserCombinator/releases) ([指定可能Version](https://github.com/ksgwr/mvn-repo/tree/gh-pages/jp/ksgwr/JParserCombinator))


pom.xmlに下記を設定します。

```
<repositories>
  <repository>
    <id>ksgwr-repo</id>
    <url>http://ksgwr.github.io/mvn-repo/</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>jp.ksgwr</groupId>
    <artifactId>JParserCombinator</artifactId>
    <version>0.0.2</version>
  </dependency>
</dependencies>
```

## サンプル

```
// CSVファイルを読み込む
Reader input = new InputStreamReader(new FileInputStream(new File("input.csv")))
Parser<Iterator<List<String>>> parser = new CSVParser();

Iterator<List<String>> iterator = parser.parse(input);
while(iterator.hasNext()) {
  List<String> fields = iterator.next();
  // Field毎の処理
  // ...
}
```

```
// Queryをパースする
String query = "A -B \"C D\" (E F)"
Parser<List<Term>> parser = new QueryParser();
List<Term> terms = parser.parse(query);

System.out.println(terms.size()); // 4
```

## 使い方

CSVParserやQueryParserの実装を参考にしてください。

## ライセンス

Apache License, Version 2.0
