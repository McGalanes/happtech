package com.github.mcgalanes.happtech.core.data.remote.payload

import io.ktor.utils.io.ByteReadChannel

object FakeRijksMuseumPayloads {
    fun getCollectionCorrectPayload(): ByteReadChannel = """
        {
          "elapsedMilliseconds": 0,
          "count": 3491,
          "artObjects": [
            {
              "links": {
                "self": "http://www.rijksmuseum.nl/api/nl/collection/SK-C-5",
                "web": "http://www.rijksmuseum.nl/nl/collectie/SK-C-5"
              },
              "id": "nl-SK-C-5",
              "objectNumber": "SK-C-5",
              "title": "De Nachtwacht",
              "hasImage": true,
              "principalOrFirstMaker": "Rembrandt van Rijn",
              "longTitle": "De Nachtwacht, Rembrandt van Rijn, 1642",
              "showImage": true,
              "permitDownload": true,
              "webImage": {
                  "guid": "bbd1fae8-4023-4859-8ed1-d38616aec96c",
                  "offsetPercentageX": 0,
                  "offsetPercentageY": 1,
                  "width": 5656,
                  "height": 4704,
                  "url":"https://lh3.googleusercontent.com/SsEIJWka3_cYRXXSE8VD3XNOgtOxoZhqW1uB6UFj78eg8gq3G4jAqL4Z_5KwA12aD7Leqp27F653aBkYkRBkEQyeKxfaZPyDx0O8CzWg=s0"
              },
              "headerImage": {
                "guid": "29a2a516-f1d2-4713-9cbd-7a4458026057",
                "offsetPercentageX": 0,
                "offsetPercentageY": 0,
                "width": 1920,
                "height": 460,
                "url": "https://lh3.googleusercontent.com/nAHNYM604vhPa1hbE_hBJw55JI01-ls0zCwHwvDEES38PpGyGHMgG_vaigVWSuK8GFkaxfn2Dmevw_Nmujn5dKW3jItgS6QaSI8VIsiH=s0"
              },
              "productionPlaces": ["Amsterdam"]
            }
          ]
        }
    """.trimIndent().let(::ByteReadChannel)

    fun getCollectionWrongPayload(): ByteReadChannel = """
        {
          "elapsedMilliseconds": 0,
          "count": 3491,
          "artObjects": [
            {
              "links": {
                "self": "http://www.rijksmuseum.nl/api/nl/collection/SK-C-5",
                "web": "http://www.rijksmuseum.nl/nl/collectie/SK-C-5"
              },
              "id": "nl-SK-C-5",
              "objectNumber": "SK-C-5",
              "title": "De Nachtwacht",
              "hasImage": true,
              "longTitle": "De Nachtwacht, Rembrandt van Rijn, 1642",
              "showImage": true,
              "permitDownload": true,
              "headerImage": {
                "guid": "29a2a516-f1d2-4713-9cbd-7a4458026057",
                "offsetPercentageX": 0,
                "offsetPercentageY": 0,
                "width": 1920,
                "height": 460,
                "url": "https://lh3.googleusercontent.com/nAHNYM604vhPa1hbE_hBJw55JI01-ls0zCwHwvDEES38PpGyGHMgG_vaigVWSuK8GFkaxfn2Dmevw_Nmujn5dKW3jItgS6QaSI8VIsiH=s0"
              },
              "productionPlaces": ["Amsterdam"]
            }
          ]
        }
    """.trimIndent().let(::ByteReadChannel)

    fun getArtObjectDetailCorrectPayload(): ByteReadChannel = """
        {
           "elapsedMilliseconds":154,
           "artObject":{
              "links":{
                 "search":"http://www.rijksmuseum.nl/api/nl/collection"
              },
              "id":"en-BK-2000-17",
              "priref":"356254",
              "objectNumber":"BK-2000-17",
              "language":"en",
              "title":"Self-portrait",
              "copyrightHolder":null,
              "webImage":{
                 "guid":"876a46b0-9d66-4d1f-8a29-0ad06c2bade8",
                 "offsetPercentageX":50,
                 "offsetPercentageY":34,
                 "width":5940,
                 "height":4992,
                 "url":"https://lh3.googleusercontent.com/FjUHATQW1qcRggtsD9L9SXeeFHVpBCvQ-taIof6oRh2U_6-wI6Oycr7As85EEty5_hXypsdLdhs7HZQay4aRnWf_xGhnAVb23olEUes=s0"
              },
              "colors":[
                 {
                    "percentage":3,
                    "hex":"#645B53"
                 },
                 {
                    "percentage":5,
                    "hex":" #7D7168"
                 },
                 {
                    "percentage":4,
                    "hex":" #4F3F34"
                 },
                 {
                    "percentage":1,
                    "hex":" #221A14"
                 },
                 {
                    "percentage":46,
                    "hex":" #9D9084"
                 },
                 {
                    "percentage":38,
                    "hex":" #C9BBB1"
                 }
              ],
              "colorsWithNormalization":[
                 {
                    "originalHex":"#645B53",
                    "normalizedHex":"#737C84"
                 },
                 {
                    "originalHex":" #7D7168",
                    "normalizedHex":"#737C84"
                 },
                 {
                    "originalHex":" #4F3F34",
                    "normalizedHex":"#737C84"
                 },
                 {
                    "originalHex":" #221A14",
                    "normalizedHex":"#000000"
                 },
                 {
                    "originalHex":" #9D9084",
                    "normalizedHex":"#737C84"
                 },
                 {
                    "originalHex":" #C9BBB1",
                    "normalizedHex":"#F6ECF3"
                 }
              ],
              "normalizedColors":[
                 {
                    "percentage":46,
                    "hex":"#808080"
                 },
                 {
                    "percentage":38,
                    "hex":" #C0C0C0"
                 },
                 {
                    "percentage":13,
                    "hex":" #696969"
                 },
                 {
                    "percentage":1,
                    "hex":" #000000"
                 }
              ],
              "normalized32Colors":[
                 {
                    "percentage":59,
                    "hex":"#737C84"
                 },
                 {
                    "percentage":38,
                    "hex":" #F6ECF3"
                 },
                 {
                    "percentage":1,
                    "hex":" #000000"
                 }
              ],
              "materialsThesaurus":[
                 
              ],
              "techniquesThesaurus":[
                 
              ],
              "productionPlacesThesaurus":[
                 
              ],
              "titles":[
                 "Self-Portrait"
              ],
              "description":"Zelfportret in de vorm van een buste, van witbakkende terracotta en polychrome beschildering. Het portret heeft ontblote schouders en een zijwaarts gericht gelaat.  Op de achterzijde een etiket met in rood-bruine inkt: JEAN DE BOULOGNE/COLLECTION PAUL DE PRAUN met het nummer 296.",
              "labelText":null,
              "objectTypes":[
                 "bust"
              ],
              "objectCollection":[
                 "sculptures"
              ],
              "makers":[
                 
              ],
              "principalMakers":[
                 {
                    "name":"Johan Gregor van der Schardt",
                    "unFixedName":"Schardt, Johan Gregor van der",
                    "placeOfBirth":"Nijmegen",
                    "dateOfBirth":"1530",
                    "dateOfBirthPrecision":null,
                    "dateOfDeath":"1581",
                    "dateOfDeathPrecision":"c.",
                    "placeOfDeath":"Neurenberg",
                    "occupation":[
                       "sculptor"
                    ],
                    "roles":[
                       "sculptor"
                    ],
                    "nationality":"Noord-Nederlands",
                    "biography":null,
                    "productionPlaces":[
                       
                    ],
                    "qualification":null,
                    "labelDesc":"Johan Gregor van der Schardt (1530 - c. 1581)"
                 }
              ],
              "plaqueDescriptionDutch":"Om dit kleine borstbeeld – het is de helft van de ware grootte – te maken, moest de beeldhouwer allerlei kunstgrepen met spiegels verrichten. Van der Schardt heeft zichzelf niet recht van voren weergegeven, maar hij wendt zijn blik van de beschouwer af, alsof hij deze niet wil zien. Het naakte bovenlichaam is een duidelijke verwijzing naar de beeldhouwkunst van de antieke oudheid.",
              "plaqueDescriptionEnglish":null,
              "principalMaker":"Johan Gregor van der Schardt",
              "artistRole":null,
              "associations":[
                 
              ],
              "acquisition":{
                 "method":"purchase",
                 "date":"2000-01-01T00:00:00",
                 "creditLine":"Purchased with the support of the Mondriaan Stichting, the SponsorBingo Loterij and the Vereniging Rembrandt, with additional funding from the Prins Bernhard Cultuurfonds"
              },
              "exhibitions":[
                 
              ],
              "materials":[
                 "terracotta (clay material)",
                 "oil paint (paint)"
              ],
              "techniques":[
                 "modeling",
                 "fired",
                 "painting technique"
              ],
              "productionPlaces":[
                 
              ],
              "dating":{
                 "presentingDate":"1573",
                 "sortingDate":1573,
                 "period":16,
                 "yearEarly":1573,
                 "yearLate":1573
              },
              "classification":{
                 "iconClassIdentifier":[
                    "48C23"
                 ],
                 "iconClassDescription":[
                    "portrait, self-portrait of sculptor"
                 ],
                 "motifs":[
                    
                 ],
                 "events":[
                    
                 ],
                 "periods":[
                    
                 ],
                 "places":[
                    
                 ],
                 "people":[
                    "Schardt, Johan Gregor van der"
                 ],
                 "objectNumbers":[
                    "BK-2000-17"
                 ]
              },
              "hasImage":true,
              "historicalPersons":[
                 "Schardt, Johan Gregor van der"
              ],
              "inscriptions":[
                 
              ],
              "documentation":[
                 "Frits Scholten, 'Michelangelo's Mighty Models or the Legacy of Johan Gregor van der Schardt', in Stephan Koja en Claudia Kryza-Gersch (red.): Shadows of Time : Giambologna, Michelangelo and the Medici Chapel, Dresden 2018, p. 92-93, fig. 1, 3.",
                 "D. Gallo, 'Small portraits for Great Men. The miniature Portrait Bust in the Sixteenth Century', The Rijksmuseum Bulletin 58 (2010) nr. 1, p. 60-62.",
                 "Peter Hecht, 'Over liefde en bedrog : hoe de top drie gekozen werd', Bulletin van de Vereniging Rembrandt 19 (2009) nr. 1, p. 37-39, nr. 24.",
                 "F. Scholten, Johan Gregor van der Schardt and the moment of self-portraiture in sculpture, in: Simiolus 33 (2007/2008), nr. 4, p. 195, afb. 1, 10",
                 "F. Scholten, Spiriti veramente divini: sculptors form the Low Countries in Italy, in: I. Alexander-Skipnes, Cultural exchange between the Low Countries and Italy (1400-1600), Turnhout 2007, p. 231.",
                 "Hans Günter Hockerts, 'In Fahrt und auf Kurs : Die Neue Deutsche Biographie', Akademie Aktuell : Zeitschrift der Bayerischen Akademie der Wissenschaften (2005, nr. 2), p. 44, afb.",
                 "A. Wallert, 'Questions and answers. The technical examination of polychrome terracotta sculptures by Johan Gregor van der Schardt', Art Matters, Netherlands Technical Studies in Art (2002), p. 32-45.",
                 "Marcella van der Weg, 'Mondriaan Stichting steunt met beleid' Rijksmuseum Kunstkrant 28, nr. 1 (2002), p. 20-21 met afb.",
                 "F. Scholten, 'Zelfportret', Bulletin van de Vereniging Rembrandt 10, nr. 3 (2000), p. 12-14 met afb..",
                 "Frankfurter Algemeine Zeitung, 8 november 2000.",
                 "A. Butterfield, Masterpieces of Renaissance sculpture : Salander-O'Reilly Galleries, New York, 2000, cat.nr. 9.",
                 "Jaarverslag : Rijksmuseum Amsterdam (2000), p. 71 afb.",
                 "'Recent acquisitions at the Rijksmuseum, Amsterdam', The Burlington Magazine (November 2000), p. 730 met afb. II.",
                 "''Rijks' koopt zelfportret 16de-eeuwse kunstenaar', De Volkskrant, 2000.",
                 "J.A. Boerner, Verzeichnis des Anton Paul Heinlein'schen ausgeschechneten Kunst-cabinetts, welches vom 1832 an durch den Auctionator J.A. Boerner [...] versteigert wird, Neurenberg, 1832.",
                 "Documentatiemap Beeldhouwkunst & Kunstnijverheid."
              ],
              "catRefRPK":[
                 
              ],
              "principalOrFirstMaker":"Johan Gregor van der Schardt",
              "dimensions":[
                 {
                    "unit":"cm",
                    "type":"height",
                    "precision":null,
                    "part":null,
                    "value":"23"
                 },
                 {
                    "unit":"cm",
                    "type":"width",
                    "precision":null,
                    "part":null,
                    "value":"28"
                 },
                 {
                    "unit":"cm",
                    "type":"depth",
                    "precision":null,
                    "part":null,
                    "value":"14"
                 }
              ],
              "physicalProperties":[
                 
              ],
              "physicalMedium":"white-firing clay and polychromy (oil paint)",
              "longTitle":"Self-portrait, Johan Gregor van der Schardt, 1573",
              "subTitle":"h 23cm × w 28cm × d 14cm",
              "scLabelLine":"Johan Gregor van der Schardt (c. 1530–1581), Nuremberg, c. 1573, white terracotta, oil paint",
              "label":{
                 "title":"Self-portrait",
                 "makerLine":"Johan Gregor van der Schardt (c. 1530–1581), Nuremberg, c. 1573, white terracotta, oil paint",
                 "description":"To make this small bust – it is half life-size – the sculptor had to resort to all kinds of tricks with a mirror. Van der Schardt did not portray himself frontally, but with his head turned sideways, as if to avoid looking at the viewer. The nude upper torso alludes to sculpture from Classical antiquity.",
                 "notes":"Multimediatour, 435.",
                 "date":"2023-10-01"
              },
              "showImage":true,
              "location":"HG-2.3"
           },
           "artObjectPage":{
              "id":"en-BK-2000-17",
              "similarPages":[
                 
              ],
              "lang":"en",
              "objectNumber":"BK-2000-17",
              "tags":[
                 
              ],
              "plaqueDescription":"Om dit kleine borstbeeld – het is de helft van de ware grootte – te maken, moest de beeldhouwer allerlei kunstgrepen met spiegels verrichten. Van der Schardt heeft zichzelf niet recht van voren weergegeven, maar hij wendt zijn blik van de beschouwer af, alsof hij deze niet wil zien. Het naakte bovenlichaam is een duidelijke verwijzing naar de beeldhouwkunst van de antieke oudheid.",
              "audioFile1":null,
              "audioFileLabel1":null,
              "audioFileLabel2":null,
              "createdOn":"2012-08-09T14:25:36.2397528+00:00",
              "updatedOn":"2012-08-09T14:25:36.2397528+00:00",
              "adlibOverrides":{
                 "titel":null,
                 "maker":null,
                 "etiketText":null
              }
           }
        }
    """.trimIndent().let(::ByteReadChannel)

    fun getArtObjectDetailWrongPayload(): ByteReadChannel = """
        {
           "elapsedMilliseconds":154,
           "artObject":{
              "links":{
                 "search":"http://www.rijksmuseum.nl/api/nl/collection"
              },
              "id":"en-BK-2000-17",
              "priref":"356254",
              "objectNumber":"BK-2000-17",
              "language":"en",
              "title":"Self-portrait",
        }
    """.trimIndent().let(::ByteReadChannel)
}
